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
    var merchant = {
        tableId: "merchantTable",    //表格id
        condition: {
            name: "",
            code:""
        }
    };

    /**
     * 初始化表格的列
     */
    merchant.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'roleId', hide: true, sort: true, title: '角色id'},
            {field: 'name', align: "center", sort: true, title: '名称'},
            {field: 'pName', align: "center", sort: true, title: '上级角色'},
            {field: 'description', align: "center", sort: true, title: '别名'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    merchant.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(merchant.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 弹出添加角色
     */
    merchant.openAddRole = function () {
        func.open({
            height: 470,
            title: '添加角色',
            content: Feng.ctxPath + '/role/merchant_add',
            tableId: merchant.tableId
        });
    };

    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    merchant.onEditRole = function (data) {
        func.open({
            height: 470,
            title: '修改角色',
            content: Feng.ctxPath + "/merchant/merchant_edit?merchantId=" + data.merchantId,
            tableId: merchant.tableId
        });
    };

    /**
     * 导出excel按钮
     */
    merchant.exportExcel = function () {
        var checkRows = table.checkStatus(merchant.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击删除角色
     *
     * @param data 点击按钮时候的行数据
     */
    merchant.onDeleteRole = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/merchant/remove", function () {
                Feng.success("删除成功!");
                table.reload(merchant.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("merchantId", data.merchantId);
            ajax.start();
        };
        Feng.confirm("是否删除角色 " + data.name + "?", operation);
    };

    /**
     * 分配菜单
     *
     * @param data 点击按钮时候的行数据
     */
    merchant.roleAssign = function (data) {
        parent.layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/merchant/merchant_assign/' + data.merchantId,
            end: function () {
                table.reload(merchant.tableId);
            }
        });
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + merchant.tableId,
        url: Feng.ctxPath + '/merchant/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: merchant.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        merchant.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        merchant.openAddmerchant();
    });

    // 导出excel
    $('#btnExp').click(function () {
        merchant.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + merchant.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            merchant.onEditmerchant(data);
        } else if (layEvent === 'delete') {
            merchant.onDeletemerchant(data);
        } else if (layEvent === 'merchantAssign') {
            merchant.merchantAssign(data);
        }
    });
});
