var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'cordova-plugin-alarmclock', 'coolMethod', [arg0]);
};

exports.createAlarmClock = function (arg0, success, error) {
    exec(success, error, 'cordova-plugin-alarmclock', 'createAlarmClock', [arg0]);
};
