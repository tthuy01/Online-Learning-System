package model;

public class SettingType {
    int settingTypeId;
    String settingTypeName;

    public SettingType() {
    }

    public SettingType(int settingTypeId, String settingTypeName) {
        this.settingTypeId = settingTypeId;
        this.settingTypeName = settingTypeName;
    }

    public int getSettingTypeId() {
        return settingTypeId;
    }

    public void setSettingTypeId(int settingTypeId) {
        this.settingTypeId = settingTypeId;
    }

    public String getSettingTypeName() {
        return settingTypeName;
    }

    public void setSettingTypeName(String settingTypeName) {
        this.settingTypeName = settingTypeName;
    }
    
}
