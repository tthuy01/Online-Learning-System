package model;

public class SettingStatus {
    int settingStatusId;
    String settingStatusName;

    public SettingStatus() {
    }

    public SettingStatus(int settingStatusId, String settingStatusName) {
        this.settingStatusId = settingStatusId;
        this.settingStatusName = settingStatusName;
    }

    public int getSettingStatusId() {
        return settingStatusId;
    }

    public void setSettingStatusId(int settingStatusId) {
        this.settingStatusId = settingStatusId;
    }

    public String getSettingStatusName() {
        return settingStatusName;
    }

    public void setSettingStatusName(String settingStatusName) {
        this.settingStatusName = settingStatusName;
    }
    
}
