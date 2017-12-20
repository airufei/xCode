var tabCache = new Object;

$(function () {
    //缓存已打开的tab，再次打开同一个tab时，选中已打开的tab
    $('#tabs').tabs({
        onSelect: function (title, index) {
            tabCache[title] = index;
        },
        onBeforeClose: function (title, index) {
            return confirm('您确定要关闭“' + title + '”吗？');
        },
        onClose: function (title, index) {
            delete tabCache[title];
        }
    });
});

function addNewTab(title, url) {
    if (tabCache[title] == undefined) {
        if (url == '') return;
        $('#tabs').tabs('add', {
            title: title,
            content: '<iframe width="100%" height="99%" frameborder="0" marginwidth="5" marginheight="5" ' +
                ' allowtransparency="yes" src="' + url + '" ></iframe>',
            closable: true
        });
    } else {
        $('#tabs').tabs('select', parseInt(tabCache[title]));
    }
}
