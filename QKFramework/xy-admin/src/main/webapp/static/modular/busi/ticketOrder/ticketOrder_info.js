/**
 * 初始化订单管理详情对话框
 */
var TicketOrderInfoDlg = {
    ticketOrderInfoData : {}
};

/**
 * 清除数据
 */
TicketOrderInfoDlg.clearData = function() {
    this.ticketOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketOrderInfoDlg.set = function(key, val) {
    this.ticketOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TicketOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TicketOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.TicketOrder.layerIndex);
}

/**
 * 收集数据
 */
TicketOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('singleNum')
    .set('singleNumPrivilege')
    .set('doubleNum')
    .set('doubleNumPrivilege')
    .set('status')
    .set('ticketNum')
    .set('ticketPerson')
    .set('ticketPersonIdentity')
    .set('ticketPersonPhone')
    .set('createUserid')
    .set('createTime')
    .set('updateUserid')
    ;
}

/**
 * 提交添加
 */
TicketOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.TicketOrder.table.refresh();
        TicketOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TicketOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ticketOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.TicketOrder.table.refresh();
        TicketOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ticketOrderInfoData);
    ajax.start();
}
/**
 * 提交出票
 */
TicketOrderInfoDlg.outTicketSubmit = function() {
	
	this.clearData();
	this.collectData();
	
	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/ticketOrder/outTicket", function(data){
		Feng.success("修改成功!");
		window.parent.TicketOrder.table.refresh();
		TicketOrderInfoDlg.close();
	},function(data){
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.ticketOrderInfoData);
	ajax.start();
}

$(function() {

});
