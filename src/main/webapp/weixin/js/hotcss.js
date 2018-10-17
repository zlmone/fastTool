(function(window, document) {
    var hotcss = {};
    var scale = 1,
        content = 'width=device-width, initial-scale=' + scale + ', minimum-scale=' + scale + ', maximum-scale=' + scale + ', user-scalable=no';
    var viewportEl = document.createElement('meta');
    viewportEl.setAttribute('name', 'viewport');
    viewportEl.setAttribute('content', content);
    document.head.appendChild(viewportEl);
    hotcss.rem2px = function(rem, designWidth) {
        return rem / 100 * designWidth;
    }
    window.hotcss = hotcss;
})(window, document);