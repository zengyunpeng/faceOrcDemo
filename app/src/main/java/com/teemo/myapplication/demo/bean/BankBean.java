package com.teemo.myapplication.demo.bean;

/**
 * description ： TODO:类的作用
 * author : teemo
 * email :
 * date : 2019/5/17 15:56
 */
public class BankBean {

    /**
     * time_used : 593
     * request_id : 1505886713,236eb3a1-55fe-4e55-ae69-780dd1b2f2d1
     * number : 1234 5678 9012 3456
     * result : 1001
     * position : {"left_bottom":{"y":914,"x":453},"right_top":{"y":138,"x":1598},"right_bottom":{"y":879,"x":1621},"left_top":{"y":173,"x":431}}
     * name : null
     * organization : null
     * valid_date : null
     * bank : null
     */

    private int time_used;
    private String request_id;
    private String number;
    private int result;
    private PositionBean position;
    private String name;
    private String organization;
    private String valid_date;
    private String bank;

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public PositionBean getPosition() {
        return position;
    }

    public void setPosition(PositionBean position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getValid_date() {
        return valid_date;
    }

    public void setValid_date(String valid_date) {
        this.valid_date = valid_date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public static class PositionBean {
        /**
         * left_bottom : {"y":914,"x":453}
         * right_top : {"y":138,"x":1598}
         * right_bottom : {"y":879,"x":1621}
         * left_top : {"y":173,"x":431}
         */

        private LeftBottomBean left_bottom;
        private RightTopBean right_top;
        private RightBottomBean right_bottom;
        private LeftTopBean left_top;

        public LeftBottomBean getLeft_bottom() {
            return left_bottom;
        }

        public void setLeft_bottom(LeftBottomBean left_bottom) {
            this.left_bottom = left_bottom;
        }

        public RightTopBean getRight_top() {
            return right_top;
        }

        public void setRight_top(RightTopBean right_top) {
            this.right_top = right_top;
        }

        public RightBottomBean getRight_bottom() {
            return right_bottom;
        }

        public void setRight_bottom(RightBottomBean right_bottom) {
            this.right_bottom = right_bottom;
        }

        public LeftTopBean getLeft_top() {
            return left_top;
        }

        public void setLeft_top(LeftTopBean left_top) {
            this.left_top = left_top;
        }

        public static class LeftBottomBean {
            /**
             * y : 914
             * x : 453
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

        public static class RightTopBean {
            /**
             * y : 138
             * x : 1598
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

        public static class RightBottomBean {
            /**
             * y : 879
             * x : 1621
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

        public static class LeftTopBean {
            /**
             * y : 173
             * x : 431
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
