package com.teemo.myapplication.demo.bean;

/**
 * description ： TODO:类的作用
 * author : teemo
 * email :
 * date : 2019/5/17 14:44
 */
public class BackPageBean {


    /**
     * result : 1001
     * time_used : 619
     * completeness : 0
     * legality : {"Edited":0,"ID_Photo_Threshold":0.8,"Photocopy":0,"Temporary_ID_Photo":0,"ID_Photo":0.999,"Screen":0.001}
     * request_id : 1527069951,2e8e2b3e-c06d-4771-8960-aad21f7b792a
     * valid_date_start : {"quality":0.921,"result":"20140701","rect":{"rt":{"y":931,"x":751},"lt":{"y":939,"x":388},"lb":{"y":973,"x":389},"rb":{"y":964,"x":753}},"logic":0}
     * issued_by : {"quality":0.902,"result":"北京市海淀区公安局","rect":{"rt":{"y":853,"x":752},"lt":{"y":861,"x":391},"lb":{"y":897,"x":392},"rb":{"y":888,"x":755}},"logic":0}
     * valid_date_end : {"quality":0.921,"result":"20240701","rect":{"rt":{"y":931,"x":751},"lt":{"y":939,"x":388},"lb":{"y":973,"x":389},"rb":{"y":964,"x":753}},"logic":0}
     * card_rect : {"rt":{"y":451,"x":881},"lt":{"y":458,"x":5},"lb":{"y":1038,"x":4},"rb":{"y":1014,"x":926}}
     * side : 1
     */

    private int result;
    private int time_used;
    private int completeness;
    private LegalityBean legality;
    private String request_id;
    private ValidDateStartBean valid_date_start;
    private IssuedByBean issued_by;
    private ValidDateEndBean valid_date_end;
    private CardRectBean card_rect;
    private int side;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public int getCompleteness() {
        return completeness;
    }

    public void setCompleteness(int completeness) {
        this.completeness = completeness;
    }

    public LegalityBean getLegality() {
        return legality;
    }

    public void setLegality(LegalityBean legality) {
        this.legality = legality;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public ValidDateStartBean getValid_date_start() {
        return valid_date_start;
    }

    public void setValid_date_start(ValidDateStartBean valid_date_start) {
        this.valid_date_start = valid_date_start;
    }

    public IssuedByBean getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(IssuedByBean issued_by) {
        this.issued_by = issued_by;
    }

    public ValidDateEndBean getValid_date_end() {
        return valid_date_end;
    }

    public void setValid_date_end(ValidDateEndBean valid_date_end) {
        this.valid_date_end = valid_date_end;
    }

    public CardRectBean getCard_rect() {
        return card_rect;
    }

    public void setCard_rect(CardRectBean card_rect) {
        this.card_rect = card_rect;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public static class LegalityBean {
        /**
         * Edited : 0
         * ID_Photo_Threshold : 0.8
         * Photocopy : 0
         * Temporary_ID_Photo : 0
         * ID_Photo : 0.999
         * Screen : 0.001
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

    public static class ValidDateStartBean {
        /**
         * quality : 0.921
         * result : 20140701
         * rect : {"rt":{"y":931,"x":751},"lt":{"y":939,"x":388},"lb":{"y":973,"x":389},"rb":{"y":964,"x":753}}
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
             * rt : {"y":931,"x":751}
             * lt : {"y":939,"x":388}
             * lb : {"y":973,"x":389}
             * rb : {"y":964,"x":753}
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
                 * y : 931
                 * x : 751
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
                 * y : 939
                 * x : 388
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
                 * y : 973
                 * x : 389
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
                 * y : 964
                 * x : 753
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

    public static class IssuedByBean {
        /**
         * quality : 0.902
         * result : 北京市海淀区公安局
         * rect : {"rt":{"y":853,"x":752},"lt":{"y":861,"x":391},"lb":{"y":897,"x":392},"rb":{"y":888,"x":755}}
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
             * rt : {"y":853,"x":752}
             * lt : {"y":861,"x":391}
             * lb : {"y":897,"x":392}
             * rb : {"y":888,"x":755}
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
                 * y : 853
                 * x : 752
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
                 * y : 861
                 * x : 391
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
                 * y : 897
                 * x : 392
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
                 * y : 888
                 * x : 755
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

    public static class ValidDateEndBean {
        /**
         * quality : 0.921
         * result : 20240701
         * rect : {"rt":{"y":931,"x":751},"lt":{"y":939,"x":388},"lb":{"y":973,"x":389},"rb":{"y":964,"x":753}}
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
             * rt : {"y":931,"x":751}
             * lt : {"y":939,"x":388}
             * lb : {"y":973,"x":389}
             * rb : {"y":964,"x":753}
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
                 * y : 931
                 * x : 751
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
                 * y : 939
                 * x : 388
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
                 * y : 973
                 * x : 389
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
                 * y : 964
                 * x : 753
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
         * rt : {"y":451,"x":881}
         * lt : {"y":458,"x":5}
         * lb : {"y":1038,"x":4}
         * rb : {"y":1014,"x":926}
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
             * y : 451
             * x : 881
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
             * y : 458
             * x : 5
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
             * y : 1038
             * x : 4
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
             * y : 1014
             * x : 926
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
