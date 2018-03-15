/**
 * 支付监控管理初始化
 */
var PayFlow = {
    id: "PayFlowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayFlow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '流水号', field: 'seq', visible: true, align: 'center', valign: 'middle'},
            {title: '（支付状态【0：支付成功；1：支付流程创建；2：支付请求发生；3:收到支付响应】）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'orderId', visible: true, align: 'center', valign: 'middle'},
            {title: '执行类', field: 'excutedClass', visible: true, align: 'center', valign: 'middle'},
            {title: '流程定义ID', field: 'processId', visible: true, align: 'center', valign: 'middle'},
            {
                title: '操作', visible: true, align: 'center', valign: 'middle', formatter: function (value, row, index) {
                	  return '<button type="button" class="btn btn-primary button-margin" onclick="PayFlow.findRecord(' + row.seq + ')" id=""><i class="fa fa-edit"></i>&nbsp;查看</button>' +
                      '<button type="button" class="btn btn-primary button-margin" onclick="PayFlow.excuteProcess(' + row.processId + ')" id=""><i class="fa fa-compass"></i>&nbsp;执行</button>';
            }
            }
    ];
};

/**
 * 流程详情
 */
PayFlow.findRecord = function (id) {
    var index = layer.open({
        type: 2,
        title: '流程详情',
        area: ['1000px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payFlow/payFlow_view/' + id
    });
    this.layerIndex = index;
};

/**
 * 执行流程
 */
PayFlow.excuteProcess = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/payFlow/excute/", function (data) {
        Feng.success("执行成功!");
        Expense.table.refresh();
    }, function (data) {
        Feng.error("执行失败!" + data.responseJSON.message + "!");
    });
    ajax.set("insId", id);
    ajax.start();
};


/**
 * 检查是否选中
 */
PayFlow.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayFlow.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加支付监控
 */
PayFlow.openAddPayFlow = function () {
    var index = layer.open({
        type: 2,
        title: '添加支付监控',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payFlow/payFlow_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看支付监控详情
 */
PayFlow.openPayFlowDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '支付监控详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payFlow/payFlow_update/' + PayFlow.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除支付监控
 */
PayFlow.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payFlow/delete", function (data) {
            Feng.success("删除成功!");
            PayFlow.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payFlowId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询支付监控列表
 */
PayFlow.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayFlow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayFlow.initColumn();
    var table = new BSTable(PayFlow.id, "/payFlow/list", defaultColunms);
    table.setPaginationType("client");
    PayFlow.table = table.init();
});
