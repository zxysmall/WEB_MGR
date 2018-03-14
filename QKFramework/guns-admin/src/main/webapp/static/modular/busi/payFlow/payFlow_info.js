/**
 * 初始化支付监控详情对话框
 */
var PayFlowInfoDlg = {
    payFlowInfoData : {}
};

/**
 * 清除数据
 */
PayFlowInfoDlg.clearData = function() {
    this.payFlowInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayFlowInfoDlg.set = function(key, val) {
    this.payFlowInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayFlowInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayFlowInfoDlg.close = function() {
    parent.layer.close(window.parent.PayFlow.layerIndex);
}

/**
 * 收集数据
 */
PayFlowInfoDlg.collectData = function() {
    this
    .set('seq')
    .set('status')
    .set('orderId')
    .set('excutedClass')
    ;
}

/**
 * 提交添加
 */
PayFlowInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payFlow/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayFlow.table.refresh();
        PayFlowInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payFlowInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayFlowInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payFlow/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayFlow.table.refresh();
        PayFlowInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payFlowInfoData);
    ajax.start();
}

$(function() {

});
