layui.use(['layer', 'form', 'table', 'admin', 'ax', 'func'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var func = layui.func;

    /**
     * 系统管理--角色管理
     */
    var checkDetail = {
        tableId: "checkDetailTable",    //表格id
        condition: {
            merchantNo: "",
            clearDate: "",
            indexNo: "",
            tradeChannel: "",
            checkStatus: ""
        }
    };

    /**
     * 初始化表格的列
     */
    checkDetail.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', align: "center", sort: true, title: '商户名称'},
            {field: 'code', align: "center", sort: true, title: '商户编码'},
            {field: 'createBy', align: "center", sort: true, title: '创建者'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    checkDetail.search = function () {
        var queryData = {};
        queryData['merchantNo'] = $("#merchantNo").val();
        queryData['clearDate'] = $("#clearDate").val();
        queryData['clearDate'] = $("#clearDate").val();
        queryData['indexNo'] = $("#indexNo").val();
        queryData['tradeChannel'] = $("#tradeChannel").val();
        queryData['checkStatus'] = $("#checkStatus").val();
        table.reload(checkDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    // /**
    //  * 点击编辑角色
    //  *
    //  * @param data 点击按钮时候的行数据
    //  */
    // merchant.onEditRole = function (data) {
    //     func.open({
    //         height: 470,
    //         title: '修改角色',
    //         content: Feng.ctxPath + "/merchant/merchant_edit?merchantId=" + data.merchantId,
    //         tableId: merchant.tableId
    //     });
    // };

    // /**
    //  * 导出excel按钮
    //  */
    // merchant.exportExcel = function () {
    //     var checkRows = table.checkStatus(merchant.tableId);
    //     if (checkRows.data.length === 0) {
    //         Feng.error("请选择要导出的数据");
    //     } else {
    //         table.exportFile(tableResult.config.id, checkRows.data, 'xls');
    //     }
    // };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + checkDetail.tableId,
        url: Feng.ctxPath + '/checkDetail/queryList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: checkDetail.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        checkDetail.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        checkDetail.openAddmerchant();
    });

    // 导出excel
    $('#btnExp').click(function () {
        checkDetail.exportExcel();
    });

    // 工具条点击事件
    // table.on('tool(' + merchant.tableId + ')', function (obj) {
    //     var data = obj.data;
    //     var layEvent = obj.event;
    //
    //     if (layEvent === 'edit') {
    //         merchant.onEditmerchant(data);
    //     } else if (layEvent === 'delete') {
    //         merchant.onDeletemerchant(data);
    //     } else if (layEvent === 'merchantAssign') {
    //         merchant.merchantAssign(data);
    //     }
    // });
});
