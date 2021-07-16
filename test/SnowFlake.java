
public class SnowFlake {
    private final static long START_STMP = 1480166465631L;
    private final static long MERCHANTID_VALUE = 0x3FFF;

    private final static long SEQUENCE_BIT = 8;
    private final static long MERCHANTID_BIT = 14;

    private final static long MAX_MERCHANTID_NUM = -1L ^ (-1L << MERCHANTID_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);


    private final static long MERCHANTID_LEFT = SEQUENCE_BIT;
    private final static long TIMESTMP_LEFT = SEQUENCE_BIT + MERCHANTID_BIT;

    private long merchantId;
    private  static long sequence = 0L;
    private  static long lastStmp = -1L;

    public SnowFlake(long merchantId) {
        if (merchantId > MAX_MERCHANTID_NUM || merchantId < 0) {
            throw new IllegalArgumentException("merchantId can't be greater than MAX_MERCHANTID_NUM or less than 0");
        }
        this.merchantId = merchantId;
    }

    public synchronized long nextId(int test) {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT
                | merchantId << MERCHANTID_LEFT
                | sequence;
    }

    public static long getMerchantId(long id) {
        return (id >> SEQUENCE_BIT) & MERCHANTID_VALUE;
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {


        for (int i = 0; i < (1 << 12); i++) {
            SnowFlake snowFlake = new SnowFlake(2);
            long id = snowFlake.nextId(1);
            System.out.println("id : "+id);
            //System.out.println("merchantId : "+getMerchantId(id));
        }
    }
}
