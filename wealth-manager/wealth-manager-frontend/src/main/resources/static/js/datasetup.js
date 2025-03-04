function downloadInstrumentDailyData(){
    document.getElementById('requestType').value = 'downloadInstrumentDailyData';
    document.getElementById('dataSetupForm').submit();
}
function downloadUniverseDailyData(){
    document.getElementById('requestType').value = 'downloadUniverseDailyData';
    document.getElementById('dataSetupForm').submit();
}
function instrumentTypeChange(){
    document.getElementById('requestType').value = 'instrumentTypeChange';
    document.getElementById('dataSetupForm').submit();
}
