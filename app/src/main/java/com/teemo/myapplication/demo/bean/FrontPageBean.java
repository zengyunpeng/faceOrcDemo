package com.teemo.myapplication.demo.bean;

/**
 * description ： TODO:类的作用
 * author : teemo
 * email :
 * date : 2019/5/17 14:43
 */
public class FrontPageBean {

    /**
     * result : 1002
     * completeness : 1
     * name : {"quality":0.956,"result":"张三","rect":{"rt":{"y":137,"x":385},"lt":{"y":136,"x":271},"lb":{"y":180,"x":271},"rb":{"y":181,"x":385}},"logic":0}
     * time_used : 442
     * gender : {"quality":0.968,"result":"女","rect":{"rt":{"y":225,"x":309},"lt":{"y":225,"x":275},"lb":{"y":261,"x":275},"rb":{"y":261,"x":309}},"logic":0}
     * address : {"quality":0.959,"result":"北京市海淀区xxxxx","rect":{"rt":{"y":388,"x":684},"lt":{"y":384,"x":269},"lb":{"y":474,"x":270},"rb":{"y":479,"x":683}},"logic":0}
     * card_rect : {"rt":{"y":55,"x":1126},"lt":{"y":49,"x":89},"lb":{"y":680,"x":100},"rb":{"y":695,"x":1101}}
     * request_id : 1526629075,8dcd4722-9f2b-42ca-a2c3-bae3a061b20f
     * idcard_number : {"quality":0.007,"result":"110xxxxxx123456789","rect":{"rt":{"y":593,"x":970},"lt":{"y":586,"x":416},"lb":{"y":622,"x":416},"rb":{"y":630,"x":968}},"logic":1}
     * birth_month : {"quality":0.995,"result":"11","rect":{"rt":{"y":306,"x":459},"lt":{"y":306,"x":435},"lb":{"y":337,"x":435},"rb":{"y":337,"x":459}},"logic":1}
     * portrait : {"quality":0,"result":"/9j/4AAQSkZJRgABA...","rect":{"rt":{"y":307,"x":559},"lt":{"y":306,"x":526},"lb":{"y":338,"x":526},"rb":{"y":338,"x":558}},"logic":0}
     * birth_day : {"quality":0.995,"result":"19","rect":{"rt":{"y":307,"x":559},"lt":{"y":306,"x":526},"lb":{"y":338,"x":526},"rb":{"y":338,"x":558}},"logic":0}
     * nationality : {"quality":0.946,"result":"汉","rect":{"rt":{"y":230,"x":509},"lt":{"y":230,"x":474},"lb":{"y":265,"x":474},"rb":{"y":265,"x":508}},"logic":0}
     * birth_year : {"quality":0.983,"result":"1989","rect":{"rt":{"y":303,"x":347},"lt":{"y":303,"x":272},"lb":{"y":334,"x":272},"rb":{"y":335,"x":347}},"logic":1}
     * side : 0
     * legality : {"Edited":0,"ID_Photo_Threshold":0.8,"Photocopy":0,"Temporary_ID_Photo":0,"ID_Photo":0.001,"Screen":0.999}
     */

    private int result;
    private int completeness;
    private NameBean name;
    private int time_used;
    private GenderBean gender;
    private AddressBean address;
    private CardRectBean card_rect;
    private String request_id;
    private IdcardNumberBean idcard_number;
    private BirthMonthBean birth_month;
    private PortraitBean portrait;
    private BirthDayBean birth_day;
    private NationalityBean nationality;
    private BirthYearBean birth_year;
    private int side;
    private LegalityBean legality;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getCompleteness() {
        return completeness;
    }

    public void setCompleteness(int completeness) {
        this.completeness = completeness;
    }

    public NameBean getName() {
        return name;
    }

    public void setName(NameBean name) {
        this.name = name;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public GenderBean getGender() {
        return gender;
    }

    public void setGender(GenderBean gender) {
        this.gender = gender;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public CardRectBean getCard_rect() {
        return card_rect;
    }

    public void setCard_rect(CardRectBean card_rect) {
        this.card_rect = card_rect;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public IdcardNumberBean getIdcard_number() {
        return idcard_number;
    }

    public void setIdcard_number(IdcardNumberBean idcard_number) {
        this.idcard_number = idcard_number;
    }

    public BirthMonthBean getBirth_month() {
        return birth_month;
    }

    public void setBirth_month(BirthMonthBean birth_month) {
        this.birth_month = birth_month;
    }

    public PortraitBean getPortrait() {
        return portrait;
    }

    public void setPortrait(PortraitBean portrait) {
        this.portrait = portrait;
    }

    public BirthDayBean getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(BirthDayBean birth_day) {
        this.birth_day = birth_day;
    }

    public NationalityBean getNationality() {
        return nationality;
    }

    public void setNationality(NationalityBean nationality) {
        this.nationality = nationality;
    }

    public BirthYearBean getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(BirthYearBean birth_year) {
        this.birth_year = birth_year;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public LegalityBean getLegality() {
        return legality;
    }

    public void setLegality(LegalityBean legality) {
        this.legality = legality;
    }

    public static class NameBean {
        /**
         * quality : 0.956
         * result : 张三
         * rect : {"rt":{"y":137,"x":385},"lt":{"y":136,"x":271},"lb":{"y":180,"x":271},"rb":{"y":181,"x":385}}
         * logic : 0
         */

        private double quality;
        private String result;
        private RectBean rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBean getRect() {
            return rect;
        }

        public void setRect(RectBean rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBean {
            /**
             * rt : {"y":137,"x":385}
             * lt : {"y":136,"x":271}
             * lb : {"y":180,"x":271}
             * rb : {"y":181,"x":385}
             */

            private RtBean rt;
            private LtBean lt;
            private LbBean lb;
            private RbBean rb;

            public RtBean getRt() {
                return rt;
            }

            public void setRt(RtBean rt) {
                this.rt = rt;
            }

            public LtBean getLt() {
                return lt;
            }

            public void setLt(LtBean lt) {
                this.lt = lt;
            }

            public LbBean getLb() {
                return lb;
            }

            public void setLb(LbBean lb) {
                this.lb = lb;
            }

            public RbBean getRb() {
                return rb;
            }

            public void setRb(RbBean rb) {
                this.rb = rb;
            }

            public static class RtBean {
                /**
                 * y : 137
                 * x : 385
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBean {
                /**
                 * y : 136
                 * x : 271
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBean {
                /**
                 * y : 180
                 * x : 271
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBean {
                /**
                 * y : 181
                 * x : 385
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class GenderBean {
        /**
         * quality : 0.968
         * result : 女
         * rect : {"rt":{"y":225,"x":309},"lt":{"y":225,"x":275},"lb":{"y":261,"x":275},"rb":{"y":261,"x":309}}
         * logic : 0
         */

        private double quality;
        private String result;
        private RectBeanX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanX getRect() {
            return rect;
        }

        public void setRect(RectBeanX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanX {
            /**
             * rt : {"y":225,"x":309}
             * lt : {"y":225,"x":275}
             * lb : {"y":261,"x":275}
             * rb : {"y":261,"x":309}
             */

            private RtBeanX rt;
            private LtBeanX lt;
            private LbBeanX lb;
            private RbBeanX rb;

            public RtBeanX getRt() {
                return rt;
            }

            public void setRt(RtBeanX rt) {
                this.rt = rt;
            }

            public LtBeanX getLt() {
                return lt;
            }

            public void setLt(LtBeanX lt) {
                this.lt = lt;
            }

            public LbBeanX getLb() {
                return lb;
            }

            public void setLb(LbBeanX lb) {
                this.lb = lb;
            }

            public RbBeanX getRb() {
                return rb;
            }

            public void setRb(RbBeanX rb) {
                this.rb = rb;
            }

            public static class RtBeanX {
                /**
                 * y : 225
                 * x : 309
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanX {
                /**
                 * y : 225
                 * x : 275
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanX {
                /**
                 * y : 261
                 * x : 275
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanX {
                /**
                 * y : 261
                 * x : 309
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class AddressBean {
        /**
         * quality : 0.959
         * result : 北京市海淀区xxxxx
         * rect : {"rt":{"y":388,"x":684},"lt":{"y":384,"x":269},"lb":{"y":474,"x":270},"rb":{"y":479,"x":683}}
         * logic : 0
         */

        private double quality;
        private String result;
        private RectBeanXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXX {
            /**
             * rt : {"y":388,"x":684}
             * lt : {"y":384,"x":269}
             * lb : {"y":474,"x":270}
             * rb : {"y":479,"x":683}
             */

            private RtBeanXX rt;
            private LtBeanXX lt;
            private LbBeanXX lb;
            private RbBeanXX rb;

            public RtBeanXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXX rt) {
                this.rt = rt;
            }

            public LtBeanXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXX lt) {
                this.lt = lt;
            }

            public LbBeanXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXX lb) {
                this.lb = lb;
            }

            public RbBeanXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXX {
                /**
                 * y : 388
                 * x : 684
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXX {
                /**
                 * y : 384
                 * x : 269
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXX {
                /**
                 * y : 474
                 * x : 270
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXX {
                /**
                 * y : 479
                 * x : 683
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class CardRectBean {
        /**
         * rt : {"y":55,"x":1126}
         * lt : {"y":49,"x":89}
         * lb : {"y":680,"x":100}
         * rb : {"y":695,"x":1101}
         */

        private RtBeanXXX rt;
        private LtBeanXXX lt;
        private LbBeanXXX lb;
        private RbBeanXXX rb;

        public RtBeanXXX getRt() {
            return rt;
        }

        public void setRt(RtBeanXXX rt) {
            this.rt = rt;
        }

        public LtBeanXXX getLt() {
            return lt;
        }

        public void setLt(LtBeanXXX lt) {
            this.lt = lt;
        }

        public LbBeanXXX getLb() {
            return lb;
        }

        public void setLb(LbBeanXXX lb) {
            this.lb = lb;
        }

        public RbBeanXXX getRb() {
            return rb;
        }

        public void setRb(RbBeanXXX rb) {
            this.rb = rb;
        }

        public static class RtBeanXXX {
            /**
             * y : 55
             * x : 1126
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }
        }

        public static class LtBeanXXX {
            /**
             * y : 49
             * x : 89
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }
        }

        public static class LbBeanXXX {
            /**
             * y : 680
             * x : 100
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }
        }

        public static class RbBeanXXX {
            /**
             * y : 695
             * x : 1101
             */

            private int y;
            private int x;

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }
        }
    }

    public static class IdcardNumberBean {
        /**
         * quality : 0.007
         * result : 110xxxxxx123456789
         * rect : {"rt":{"y":593,"x":970},"lt":{"y":586,"x":416},"lb":{"y":622,"x":416},"rb":{"y":630,"x":968}}
         * logic : 1
         */

        private double quality;
        private String result;
        private RectBeanXXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXX {
            /**
             * rt : {"y":593,"x":970}
             * lt : {"y":586,"x":416}
             * lb : {"y":622,"x":416}
             * rb : {"y":630,"x":968}
             */

            private RtBeanXXXX rt;
            private LtBeanXXXX lt;
            private LbBeanXXXX lb;
            private RbBeanXXXX rb;

            public RtBeanXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXX {
                /**
                 * y : 593
                 * x : 970
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXX {
                /**
                 * y : 586
                 * x : 416
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXX {
                /**
                 * y : 622
                 * x : 416
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXX {
                /**
                 * y : 630
                 * x : 968
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class BirthMonthBean {
        /**
         * quality : 0.995
         * result : 11
         * rect : {"rt":{"y":306,"x":459},"lt":{"y":306,"x":435},"lb":{"y":337,"x":435},"rb":{"y":337,"x":459}}
         * logic : 1
         */

        private double quality;
        private String result;
        private RectBeanXXXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXXX {
            /**
             * rt : {"y":306,"x":459}
             * lt : {"y":306,"x":435}
             * lb : {"y":337,"x":435}
             * rb : {"y":337,"x":459}
             */

            private RtBeanXXXXX rt;
            private LtBeanXXXXX lt;
            private LbBeanXXXXX lb;
            private RbBeanXXXXX rb;

            public RtBeanXXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXXX {
                /**
                 * y : 306
                 * x : 459
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXXX {
                /**
                 * y : 306
                 * x : 435
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXXX {
                /**
                 * y : 337
                 * x : 435
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXXX {
                /**
                 * y : 337
                 * x : 459
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class PortraitBean {
        /**
         * quality : 0
         * result : /9j/4AAQSkZJRgABA...
         * rect : {"rt":{"y":307,"x":559},"lt":{"y":306,"x":526},"lb":{"y":338,"x":526},"rb":{"y":338,"x":558}}
         * logic : 0
         */

        private int quality;
        private String result;
        private RectBeanXXXXX rect;
        private int logic;

        public int getQuality() {
            return quality;
        }

        public void setQuality(int quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXXXX {
            /**
             * rt : {"y":307,"x":559}
             * lt : {"y":306,"x":526}
             * lb : {"y":338,"x":526}
             * rb : {"y":338,"x":558}
             */

            private RtBeanXXXXXX rt;
            private LtBeanXXXXXX lt;
            private LbBeanXXXXXX lb;
            private RbBeanXXXXXX rb;

            public RtBeanXXXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXXXX {
                /**
                 * y : 307
                 * x : 559
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXXXX {
                /**
                 * y : 306
                 * x : 526
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXXXX {
                /**
                 * y : 338
                 * x : 526
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXXXX {
                /**
                 * y : 338
                 * x : 558
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class BirthDayBean {
        /**
         * quality : 0.995
         * result : 19
         * rect : {"rt":{"y":307,"x":559},"lt":{"y":306,"x":526},"lb":{"y":338,"x":526},"rb":{"y":338,"x":558}}
         * logic : 0
         */

        private double quality;
        private String result;
        private RectBeanXXXXXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXXXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXXXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXXXXX {
            /**
             * rt : {"y":307,"x":559}
             * lt : {"y":306,"x":526}
             * lb : {"y":338,"x":526}
             * rb : {"y":338,"x":558}
             */

            private RtBeanXXXXXXX rt;
            private LtBeanXXXXXXX lt;
            private LbBeanXXXXXXX lb;
            private RbBeanXXXXXXX rb;

            public RtBeanXXXXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXXXXX {
                /**
                 * y : 307
                 * x : 559
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXXXXX {
                /**
                 * y : 306
                 * x : 526
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXXXXX {
                /**
                 * y : 338
                 * x : 526
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXXXXX {
                /**
                 * y : 338
                 * x : 558
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class NationalityBean {
        /**
         * quality : 0.946
         * result : 汉
         * rect : {"rt":{"y":230,"x":509},"lt":{"y":230,"x":474},"lb":{"y":265,"x":474},"rb":{"y":265,"x":508}}
         * logic : 0
         */

        private double quality;
        private String result;
        private RectBeanXXXXXXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXXXXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXXXXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXXXXXX {
            /**
             * rt : {"y":230,"x":509}
             * lt : {"y":230,"x":474}
             * lb : {"y":265,"x":474}
             * rb : {"y":265,"x":508}
             */

            private RtBeanXXXXXXXX rt;
            private LtBeanXXXXXXXX lt;
            private LbBeanXXXXXXXX lb;
            private RbBeanXXXXXXXX rb;

            public RtBeanXXXXXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXXXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXXXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXXXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXXXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXXXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXXXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXXXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXXXXXX {
                /**
                 * y : 230
                 * x : 509
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXXXXXX {
                /**
                 * y : 230
                 * x : 474
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXXXXXX {
                /**
                 * y : 265
                 * x : 474
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXXXXXX {
                /**
                 * y : 265
                 * x : 508
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class BirthYearBean {
        /**
         * quality : 0.983
         * result : 1989
         * rect : {"rt":{"y":303,"x":347},"lt":{"y":303,"x":272},"lb":{"y":334,"x":272},"rb":{"y":335,"x":347}}
         * logic : 1
         */

        private double quality;
        private String result;
        private RectBeanXXXXXXXX rect;
        private int logic;

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public RectBeanXXXXXXXX getRect() {
            return rect;
        }

        public void setRect(RectBeanXXXXXXXX rect) {
            this.rect = rect;
        }

        public int getLogic() {
            return logic;
        }

        public void setLogic(int logic) {
            this.logic = logic;
        }

        public static class RectBeanXXXXXXXX {
            /**
             * rt : {"y":303,"x":347}
             * lt : {"y":303,"x":272}
             * lb : {"y":334,"x":272}
             * rb : {"y":335,"x":347}
             */

            private RtBeanXXXXXXXXX rt;
            private LtBeanXXXXXXXXX lt;
            private LbBeanXXXXXXXXX lb;
            private RbBeanXXXXXXXXX rb;

            public RtBeanXXXXXXXXX getRt() {
                return rt;
            }

            public void setRt(RtBeanXXXXXXXXX rt) {
                this.rt = rt;
            }

            public LtBeanXXXXXXXXX getLt() {
                return lt;
            }

            public void setLt(LtBeanXXXXXXXXX lt) {
                this.lt = lt;
            }

            public LbBeanXXXXXXXXX getLb() {
                return lb;
            }

            public void setLb(LbBeanXXXXXXXXX lb) {
                this.lb = lb;
            }

            public RbBeanXXXXXXXXX getRb() {
                return rb;
            }

            public void setRb(RbBeanXXXXXXXXX rb) {
                this.rb = rb;
            }

            public static class RtBeanXXXXXXXXX {
                /**
                 * y : 303
                 * x : 347
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LtBeanXXXXXXXXX {
                /**
                 * y : 303
                 * x : 272
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class LbBeanXXXXXXXXX {
                /**
                 * y : 334
                 * x : 272
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }

            public static class RbBeanXXXXXXXXX {
                /**
                 * y : 335
                 * x : 347
                 */

                private int y;
                private int x;

                public int getY() {
                    return y;
                }

                public void setY(int y) {
                    this.y = y;
                }

                public int getX() {
                    return x;
                }

                public void setX(int x) {
                    this.x = x;
                }
            }
        }
    }

    public static class LegalityBean {
        /**
         * Edited : 0
         * ID_Photo_Threshold : 0.8
         * Photocopy : 0
         * Temporary_ID_Photo : 0
         * ID_Photo : 0.001
         * Screen : 0.999
         */

        private int Edited;
        private double ID_Photo_Threshold;
        private int Photocopy;
        private int Temporary_ID_Photo;
        private double ID_Photo;
        private double Screen;

        public int getEdited() {
            return Edited;
        }

        public void setEdited(int Edited) {
            this.Edited = Edited;
        }

        public double getID_Photo_Threshold() {
            return ID_Photo_Threshold;
        }

        public void setID_Photo_Threshold(double ID_Photo_Threshold) {
            this.ID_Photo_Threshold = ID_Photo_Threshold;
        }

        public int getPhotocopy() {
            return Photocopy;
        }

        public void setPhotocopy(int Photocopy) {
            this.Photocopy = Photocopy;
        }

        public int getTemporary_ID_Photo() {
            return Temporary_ID_Photo;
        }

        public void setTemporary_ID_Photo(int Temporary_ID_Photo) {
            this.Temporary_ID_Photo = Temporary_ID_Photo;
        }

        public double getID_Photo() {
            return ID_Photo;
        }

        public void setID_Photo(double ID_Photo) {
            this.ID_Photo = ID_Photo;
        }

        public double getScreen() {
            return Screen;
        }

        public void setScreen(double Screen) {
            this.Screen = Screen;
        }
    }
}
