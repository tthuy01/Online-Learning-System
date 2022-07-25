package model;

public class Setting {
    int settingId;
    int settingTypeId;
    String settingValue;
    String settingOrder;
    int settingStatusId;
    String settingDescription;

    public Setting() {
    }

    public Setting(int settingId, int settingTypeId, String settingValue, String settingOrder, int settingStatusId, String settingDescription) {
        this.settingId = settingId;
        this.settingTypeId = settingTypeId;
        this.settingValue = settingValue;
        this.settingOrder = settingOrder;
        this.settingStatusId = settingStatusId;
        this.settingDescription = settingDescription;
    }

    public int getSettingId() {
        return settingId;
    }

    public void setSettingId(int settingId) {
        this.settingId = settingId;
    }

    public int getSettingTypeId() {
        return settingTypeId;
    }

    public void setSettingTypeId(int settingTypeId) {
        this.settingTypeId = settingTypeId;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getSettingOrder() {
        return settingOrder;
    }

    public void setSettingOrder(String settingOrder) {
        this.settingOrder = settingOrder;
    }

    public int getSettingStatusId() {
        return settingStatusId;
    }

    public void setSettingStatusId(int settingStatusId) {
        this.settingStatusId = settingStatusId;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
    }
    
}
