(function () {
    function sleep(ms) {//via 阮一峰 《ECMAScript 6 入门》
        return new Promise(resolve => setTimeout(resolve, ms))
    }

    window.sleep = {}
    window.sleep.sleep = sleep;
})();