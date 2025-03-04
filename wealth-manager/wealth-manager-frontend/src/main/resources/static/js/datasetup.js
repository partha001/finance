function downloadInstrumentDailyData(){
//    resetting universe-name dropdown
    document.getElementById('universeName').value = '';
    document.getElementById('universeName').text = 'Select Universe name';

    document.getElementById('requestType').value = 'downloadInstrumentDailyData';
    document.getElementById('dataSetupForm').submit();
}

function downloadUniverseDailyData(){
//  resetting instrument type and name
    document.getElementById('instrumentType').value = '';
    document.getElementById('instrumentType').text = 'Select instrument type';
    document.getElementById('instrumentName').value = '';
    document.getElementById('instrumentName').text = 'Select instrument';

    document.getElementById('requestType').value = 'downloadUniverseDailyData';
    document.getElementById('dataSetupForm').submit();
}

function instrumentTypeChange(){
//    resetting universe-name dropdown
    document.getElementById('universeName').value = '';
    document.getElementById('universeName').text = 'Select Universe name';

    document.getElementById('requestType').value = 'instrumentTypeChange';
    document.getElementById('dataSetupForm').submit();
}

function universeChange(){
//  resetting instrumentType and insturmentName dropdown
    document.getElementById('instrumentType').value = '';
    document.getElementById('instrumentType').text = 'Select instrument type';
    document.getElementById('instrumentName').value = '';
    document.getElementById('instrumentName').text = 'Select instrument';

}
