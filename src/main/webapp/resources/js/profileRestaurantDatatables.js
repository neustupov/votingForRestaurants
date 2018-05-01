var ajaxUrl = "/ajax/profile/restaurants/";
var voteAjaxUrl = "/ajax/profile/votes/";
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#profileRestDatatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            }, {
                "data": "numberOfVotes"
            }, {
                "orderable": false,
                "defaultContent": "",
                "render": renderTodaysBtn
            }, {
                "orderable": false,
                "defaultContent": "",
                "render": renderVoteBtn
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

function getProfileTodaysMenuWithMeals(restId) {
    document.location.href = "getProfileTodaysMenuWithMeals?restId=" + restId;
}

function createVote(restId) {
    $.ajax({
        type: "POST",
        url: voteAjaxUrl,
        data: {"restId": restId}
    }).done(function () {
            updateTable();
            successNoty("common.saved");
        }
    );
}

function renderTodaysBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='getProfileTodaysMenuWithMeals(" + row.id + ");'>" +
            "<span class='fa fa-cutlery' aria-hidden='true'></span></a>";
    }
}

function renderVoteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='createVote(" + row.id + ");'>" +
            "<span class='fa fa-check' aria-hidden='true'></span></a>";
    }
}