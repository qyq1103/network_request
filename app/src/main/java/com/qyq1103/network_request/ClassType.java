package com.qyq1103.network_request;

import java.util.List;

/**
 * @author：静·灵
 * @date: 2020/1/7 14:41
 * 描述：数据模型
 * 功能: 数据的类型对象
 */
public class ClassType {
    private String msg;
    private int status;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int KsbClassID;
        private int PID;
        private String KsbClassName;
        private int IsMedi;
        private List<ChildsBean> Childs;
        private List<ApplistBeanX> applist;

        public int getKsbClassID() {
            return KsbClassID;
        }

        public void setKsbClassID(int KsbClassID) {
            this.KsbClassID = KsbClassID;
        }

        public int getPID() {
            return PID;
        }

        public void setPID(int PID) {
            this.PID = PID;
        }

        public String getKsbClassName() {
            return KsbClassName;
        }

        public void setKsbClassName(String KsbClassName) {
            this.KsbClassName = KsbClassName;
        }

        public int getIsMedi() {
            return IsMedi;
        }

        public void setIsMedi(int IsMedi) {
            this.IsMedi = IsMedi;
        }

        public List<ChildsBean> getChilds() {
            return Childs;
        }

        public void setChilds(List<ChildsBean> Childs) {
            this.Childs = Childs;
        }

        public List<ApplistBeanX> getApplist() {
            return applist;
        }

        public void setApplist(List<ApplistBeanX> applist) {
            this.applist = applist;
        }

        public static class ChildsBean {

            private int KsbClassID;
            private int PID;
            private String KsbClassName;
            private int IsMedi;
            private Object Childs;
            private List<ApplistBean> applist;

            public int getKsbClassID() {
                return KsbClassID;
            }

            public void setKsbClassID(int KsbClassID) {
                this.KsbClassID = KsbClassID;
            }

            public int getPID() {
                return PID;
            }

            public void setPID(int PID) {
                this.PID = PID;
            }

            public String getKsbClassName() {
                return KsbClassName;
            }

            public void setKsbClassName(String KsbClassName) {
                this.KsbClassName = KsbClassName;
            }

            public int getIsMedi() {
                return IsMedi;
            }

            public void setIsMedi(int IsMedi) {
                this.IsMedi = IsMedi;
            }

            public Object getChilds() {
                return Childs;
            }

            public void setChilds(Object Childs) {
                this.Childs = Childs;
            }

            public List<ApplistBean> getApplist() {
                return applist;
            }

            public void setApplist(List<ApplistBean> applist) {
                this.applist = applist;
            }

            public static class ApplistBean {

                private int AppID;
                private int KsbClassID;
                private String AppEName;
                private String AppName;
                private String CName;
                private int SortID;

                public int getAppID() {
                    return AppID;
                }

                public void setAppID(int AppID) {
                    this.AppID = AppID;
                }

                public int getKsbClassID() {
                    return KsbClassID;
                }

                public void setKsbClassID(int KsbClassID) {
                    this.KsbClassID = KsbClassID;
                }

                public String getAppEName() {
                    return AppEName;
                }

                public void setAppEName(String AppEName) {
                    this.AppEName = AppEName;
                }

                public String getAppName() {
                    return AppName;
                }

                public void setAppName(String AppName) {
                    this.AppName = AppName;
                }

                public String getCName() {
                    return CName;
                }

                public void setCName(String CName) {
                    this.CName = CName;
                }

                public int getSortID() {
                    return SortID;
                }

                public void setSortID(int SortID) {
                    this.SortID = SortID;
                }
            }
        }

        public static class ApplistBeanX {

            private int AppID;
            private int KsbClassID;
            private String AppEName;
            private String AppName;
            private String CName;
            private int SortID;

            public int getAppID() {
                return AppID;
            }

            public void setAppID(int AppID) {
                this.AppID = AppID;
            }

            public int getKsbClassID() {
                return KsbClassID;
            }

            public void setKsbClassID(int KsbClassID) {
                this.KsbClassID = KsbClassID;
            }

            public String getAppEName() {
                return AppEName;
            }

            public void setAppEName(String AppEName) {
                this.AppEName = AppEName;
            }

            public String getAppName() {
                return AppName;
            }

            public void setAppName(String AppName) {
                this.AppName = AppName;
            }

            public String getCName() {
                return CName;
            }

            public void setCName(String CName) {
                this.CName = CName;
            }

            public int getSortID() {
                return SortID;
            }

            public void setSortID(int SortID) {
                this.SortID = SortID;
            }
        }
    }
}
