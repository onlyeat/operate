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
     * trade_channel,merchant_no, clear_date, trade_date, index_no,trade_amount, check_status
     */
    checkDetail.initColumn = function (count) {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'tradeChannel', align: "center", sort: true, title: '交易渠道', templet: function (d) {
                    if (d.tradeChannel == "WX" )  {
                        return "微信";
                    }
                    if (d.tradeChannel == "ZFB" )  {
                        return "支付宝";
                    }
                    if (d.tradeChannel == "YSF" )  {
                        return "云闪付";
                    }
                }},
            {field: 'merchantNo', align: "center", title: '商户号'},
            {field: 'clearDate', align: "center", title: '清算日期'},
            {field: 'tradeDate', align: "center", sort: true, title: '交易日期'},
            {field: 'indexNo', align: "center", title: '流水号', totalRowText: "合计金额："},
            {field: 'tradeAmount', align: "center", title: '交易金额', totalRow: true},
            {field: 'checkStatus', align: "center", sort: true, title: '对账状态', templet: function (d) {
                    if (d.checkStatus === 0) {
                        return "正常";
                    } else {
                        return "错账";
                    }
            }}
        ]];
    };

    /**
     * 点击查询按钮
     */
    checkDetail.search = function () {
        var queryData = {};
        queryData['merchantNo'] = $("#merchantNo").val();
        queryData['clearDate'] = $("#clearDate").val();
        queryData['indexNo'] = $("#indexNo").val();
        queryData['tradeChannel'] = $("#tradeChannel").val();
        queryData['checkStatus'] = $("#checkStatus").val();
        table.reload(checkDetail.tableId, {
            where: queryData, page: {curr: 1}
        });
        getSumTradeAmount(queryData);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + checkDetail.tableId,
        url: Feng.ctxPath + '/checkDetail/queryList',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        totalRow: true, //开启合计行
        cols: checkDetail.initColumn(),
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            ,groups: 5 //只显示 1 个连续页码
            ,first: '首页' //不显示首页
            ,last: '尾页' //显示尾页
            }
        ,height: 'full-240'
        ,done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            //得到当前页码
            console.log(curr);
            //得到数据总量
            console.log(count);
            // $("#countTrade").html(count);
            // $("#sumTradeAmount").html(count);
            //获取总金额 和 笔数
            getSumTradeAmount();
        }
        // ,limit: 20
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
    function getSumTradeAmount() {
        var merchantNo = $("#merchantNo").val();
        var clearDate = $("#clearDate").val();
        var indexNo = $("#indexNo").val();
        var tradeChannel = $("#tradeChannel").val();
        var checkStatus = $("#checkStatus").val();
        $.ajax({
            type: 'get',
            url: '/checkDetail/getSumTradeAmount',
            data: {"merchantNo": merchantNo, "clearDate":clearDate,
                "indexNo": indexNo, "tradeChannel":tradeChannel,
                "checkStatus":checkStatus},
            async: false,
            success: function (jsonObj) {
                // $("#merchantIdSearch").empty();
                // $("#merchantIdSearch").append("<option value=''>请选择渠道商</option>");
                // for (var i = 0; i < jsonObj.length; i++) {
                //     $("#merchantIdSearch").append("<option value="+ jsonObj[i].id +">"+ jsonObj[i].name +"</option>");
                // }
                // renderForm();
                $("#countTrade").html(jsonObj.countTrade);
                $("#sumTradeAmount").html(jsonObj.sumTradeAmount);
            }
        });
    };
});

