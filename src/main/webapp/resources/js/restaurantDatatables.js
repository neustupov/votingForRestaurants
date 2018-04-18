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
                "data": "name"
            }, {
                "defaultContent": "Votes"
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

function redirectToVotes() {
    document.location.href="/votes"
}

function redirectToMenus(restId) {
    document.location.href="/menus?restId=" + restId;
}

function getTodaysMenuWithMeals(restId) {
    document.location.href="menus/getTodaysMenuWithMeals?restId=" + restId;
}

function createVote(restId) {
    document.location.href="votes/updateOrCreate?restId=" + restId;
}