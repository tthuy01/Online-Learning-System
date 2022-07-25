function doChange(settingId, settingStatusId) {
    if (confirm("Are you sure to change setting status of setting " + settingId + "?"))
    {
//        window.location = 'changesettingstatus?settingId=${ls.get(i).settingId}&settingStatusId=${ls.get(i).settingStatusId}';
        window.location = 'changesettingstatus?settingId='+settingId+'&settingStatusId='+settingStatusId;
    }
}
