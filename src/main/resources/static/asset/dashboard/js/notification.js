
function deleteByNotifi(id) {
    var deleteOption = {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer ' + x
        }
    };
    notification = fetch("/api/notifi/" + id, deleteOption)
        .then(response => {
            $("#notifi").load(location.href + " #notifi");
        });
}


function clearAllNotifi() {
    var deleteOption = {
        method: 'delete',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer ' + x
        }
    };
    notification = fetch("/api/notifi", deleteOption)
        .then(response => {
            $("#notifi").load(location.href + " #notifi");
        });
}

