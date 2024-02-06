var orderDetails = [];

loadAllOrderDetails();

function loadAllOrderDetails() {

    $("#orderDetailTable").empty();

    for (var orderDetail of orderDetails) {
        console.log(orderDetail);
        var row = `<tr><td>${orderDetail.orderId}</td><td>${orderDetail.cusId}</td><td>${orderDetail.total}</td><td>${orderDetail.qty}</td><td>${orderDetail.date}</td></tr>`;
        $("#tblOrderDetails").append(row);
    }
}