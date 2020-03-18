/**
 * 刷新
 */
function refurbish() {
    show(1,3);
    var index = layer.load(0, {shade: false,time: 200});
    isFirst=true;
}
/**
 * 复选框
 */
function setCheckBok(){
    if ($("input[name='ids']").length==$("input[name='ids']:checked").length){
        $("input[type='checkbox']").iCheck("check");
    }else {
        $("#selall").iCheck("uncheck");
    }
}

/**
 * 全选框
 */
function checkbox() {
    var clicks = $("#selall").is(':checked');
    if (!clicks) {
        $("#dataList td input[type='checkbox']").iCheck("uncheck");

    } else {
        $("#dataList td input[type='checkbox']").iCheck("check");
    }
    $(this).data("clicks", !clicks);
}
/**
 * 复选框id
 */
function isCheckbox() {
    var checkbox = $("input[name='ids']");
    var id = "id";
    for (var i=0;i<checkbox.length;i++){
        if (checkbox[i].checked){
            id = id+"#"+checkbox[i].value;
        }
    }
    return id;
}
/**
 * 初始化分页插件
 */
function initPagination(total,pageSize) {
    /*初始化插件*/
    $("#Pagination").pagination(total, {
        num_edge_entries: 2, //边缘页数
        num_display_entries:4, //主体页数
        callback: pageselectCallback,
        items_per_page: pageSize, //每页显示个数
        prev_text: "前一页",
        next_text: "后一页"
    });

    /**
     * 分页处理
     */
    function pageselectCallback(page_index){
        if(!isFirst){
            show(page_index + 1, pageSize);
        }
        isFirst=false;
        return false;
    }
}