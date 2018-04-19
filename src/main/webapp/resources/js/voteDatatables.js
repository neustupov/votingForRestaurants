var ajaxUrl = "ajax/admin/votes/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#voteDatatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "user.name"
            },
            {
                "data": "restaurant.name"
            },
            {
                "data": "date"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});