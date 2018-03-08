/**
 * 订单管理管理初始化
 */
var TicketOrder = {
    id: "TicketOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TicketOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle',
	        	footerFormatter: function () {
	                return "合计";
	        	}
	        },
            {title: '单票数', field: 'singleNum', visible: true, align: 'center', valign: 'middle',
            	footerFormatter: function (value) {
	                var count = 0;
	                for (var i in value) {
	                    count += value[i].singleNum;
	                }
	                return count;
            	}
            },
            {title: '单优票数', field: 'singleNumPrivilege', visible: true, align: 'center', valign: 'middle',
            	footerFormatter: function (value) {
	                var count = 0;
	                for (var i in value) {
	                    count += value[i].singleNumPrivilege;
	                }
	                return count;
            	}
            },
            {title: '双票数', field: 'doubleNum', visible: true, align: 'center', valign: 'middle',
            	footerFormatter: function (value) {
	                var count = 0;
	                for (var i in value) {
	                    count += value[i].doubleNum;
	                }
	                return count;
            	}
            },
            {title: '双优票数', field: 'doubleNumPrivilege', visible: true, align: 'center', valign: 'middle',
            	footerFormatter: function (value) {
	                var count = 0;
	                for (var i in value) {
	                    count += value[i].doubleNumPrivilege;
	                }
	                return count;
            	}
            },
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '出票号', field: 'ticketNum', visible: true, align: 'center', valign: 'middle'},
            {title: '取票人姓名', field: 'ticketPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '取票人身份证', field: 'ticketPersonIdentity', visible: true, align: 'center', valign: 'middle'},
            {title: '取票人电话', field: 'ticketPersonPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createUser', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '出票人', field: 'updateUser', visible: true, align: 'center', valign: 'middle'},
            {title: '出票时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TicketOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TicketOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中
 */
TicketOrder.checkOutTicket = function () {
	this.check();
	if(this.seItem.status == '已出'){
		 Feng.info("此订单已出票！");
	     return false;
	}else{
		return true;
	}
}

/**
 * 点击添加订单管理
 */
TicketOrder.openAddTicketOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ticketOrder/ticketOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单管理详情
 */
TicketOrder.openTicketOrderOutTicket = function () {
    if (this.checkOutTicket()) {
        var index = layer.open({
            type: 2,
            title: '订单管理详情',
            area: ['800px', '620px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ticketOrder/ticketOrder_outTicket/' + TicketOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看订单管理详情
 */
TicketOrder.openTicketOrderDetail = function () {
	if (this.checkOutTicket()) {
		var index = layer.open({
			type: 2,
			title: '订单管理详情',
			area: ['800px', '620px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/ticketOrder/ticketOrder_update/' + TicketOrder.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 删除订单管理
 */
TicketOrder.delete = function () {
    if (this.checkOutTicket()) {
        var ajax = new $ax(Feng.ctxPath + "/ticketOrder/delete", function (data) {
            Feng.success("删除成功!");
            TicketOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ticketOrderId",this.seItem.id);
        ajax.start();
    }
};

TicketOrder.resetSearch = function () {
    $("#name").val("");
    $("#status").val(1);
    $("#beginTime").val("");
    $("#endTime").val("");

    MgrUser.search();
}
/**
 * 查询订单管理列表
 */
TicketOrder.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['status'] = $("#status").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    TicketOrder.table.refresh({query: queryData});
};

TicketOrder.initInputSelect = function (id,dataUrl){      
    var _select = new InputSelect(id,dataUrl);
    _select.init();
}

$(function () {
    var defaultColunms = TicketOrder.initColumn();
    var table = new BSTable(TicketOrder.id, "/ticketOrder/list", defaultColunms,true);
    table.setPaginationType("client");
    TicketOrder.table = table.init();
    TicketOrder.initInputSelect("status","/dict/selectOption/_50");
});
