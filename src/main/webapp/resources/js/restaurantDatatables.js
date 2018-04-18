var ajaxUrl = "/ajax/admin/restaurants/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#restDatatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "id"
            }, {
                "data": "name"
            }, {
                "defaultContent": "Todays",
                "orderable": false
            }, {
                "defaultContent": "Todays",
                "orderable": false
            }, {
                "defaultContent": "All",
                "orderable": false
            }, {
                "defaultContent": "Delete",
                "orderable": false
            }, {
                "defaultContent": "Edit",
                "orderable": false
            }, {
                "defaultContent": "Vote",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ]
    });
    makeEditable();
});