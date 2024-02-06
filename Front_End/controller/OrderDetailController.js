// loadOrderDetail();
//
// function loadOrderDetail(){
//
//     $.ajax({
//         // url : "http://localhost:8080/app/orders",
//         success : function(res){
//             let orderDetail = $(res);
//             $('#orderDetailTable').empty();
//
//             for(let i = 0; i < orderDetail.length; i++){
//                 let orderId = orderDetail[i].orderId;
//                 let id = orderDetail[i].id;
//                 let total = orderDetail[i].total;
//                 let qty = orderDetail[i].qty;
//                 let date = orderDetail[i].date;
//
//                 let row =`<tr><td>${orderId}</td><td>${id}</td><td>${total}</td><td>${qty}</td><td>${date}</td></tr>`;
//                 $('#orderDetailTable').append(row);
//             }
//         }
//     });
//
// }