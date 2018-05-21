var ajaxUrl = "ajax/admin/votes/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#voteDatatable").DataTable(extendsOpts({
        "columns": [
            {
                "data": "user.name"
            },
            {
                "data": "restaurant.name"
            },
            {
                "data": "date",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    }));
});

function redirectToRestaurants() {
    document.location.href = "restaurants";
}