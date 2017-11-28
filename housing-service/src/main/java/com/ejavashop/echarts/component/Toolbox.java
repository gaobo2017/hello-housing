package com.ejavashop.echarts.component;

import java.util.ArrayList;
import java.util.List;

/**    
 * 工具集                  
 * @Filename: Toolbox.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class Toolbox {
    private boolean show = true;
    public Feature  feature;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public class Feature {
        DataView    dataView;
        MagicType   magicType;
        Restore     restore;
        SaveAsImage saveAsImage;

        public SaveAsImage getSaveAsImage() {
            return saveAsImage;
        }

        public void setSaveAsImage(SaveAsImage saveAsImage) {
            this.saveAsImage = saveAsImage;
        }

        public DataView getDataView() {
            return dataView;
        }

        public void setDataView(DataView dataView) {
            this.dataView = dataView;
        }

        public MagicType getMagicType() {
            return magicType;
        }

        public void setMagicType(MagicType magicType) {
            this.magicType = magicType;
        }

        public Restore getRestore() {
            return restore;
        }

        public void setRestore(Restore restore) {
            this.restore = restore;
        }

        public class DataView {
            boolean show     = true;
            boolean readOnly = true;

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public boolean isReadOnly() {
                return readOnly;
            }

            public void setReadOnly(boolean readOnly) {
                this.readOnly = readOnly;
            }

        }

        public class MagicType {
            boolean      show = true;
            List<String> type = new ArrayList<String>();

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public List<String> getType() {
                return type;
            }

            public void setType(List<String> type) {
                this.type = type;
            }
        }

        public class Restore {
            boolean show = true;

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

        }

        public class SaveAsImage {
            boolean show = true;

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

        }
    }

}
