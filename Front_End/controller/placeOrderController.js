// let allCustomers;
// let allItems;
//
// loadAllCusDet();
// loadAllItemDet();
//
// $('#txtCash').val("0");
// $('#txtDiscount').val("0");
// $('#txtBalance').val("0");
//
// function loadAllCusDet(){
//     $.ajax({
//         url : "http://localhost:8080/app/customers",
//         success : function(res){
//             allCustomers = $(res);
//
//             let optionCus;
//
//             for(let i = 0; i < allCustomers.length; i++){
//                 let id = allCustomers[i].id;
//                 optionCus += '<option value="' + id + '">' + id + '</option>';
//             }
//
//             $('#selectCusID').append(optionCus);
//         }
//     });
// }
//
//
//
// function loadAllItemDet(){
//     $.ajax({
//         url : "http://localhost:8080/app/items",
//         success : function(res){
//             allItems = $(res);
//
//             let optionItem;
//
//             for(let i = 0; i < allItems.length; i++){
//                 let code = allItems[i].code;
//                 optionItem += '<option value="' + code + '">' + code + '</option>';
//             }
//
//             $('#selectItemCode').append(optionItem);
//         }
//     });
// }
//
//
//
// function getItemDetails() {
//     let rows = $("#orderTable").children().length;
//     var cart = [];
//
//     for (let i = 0; i < rows; i++) {
//         let itCode = $("#orderTable").children().eq(i).children(":eq(0)").text();
//         let avQty = $("#orderTable").children().eq(i).children(":eq(3)").text();
//         let itQty = $("#orderTable").children().eq(i).children(":eq(4)").text();
//         let itPrice = $("#orderTable").children().eq(i).children(":eq(2)").text();
//         cart.push({code: itCode, avQty:avQty, qty: itQty, price: itPrice});
//     }
//
//     return cart;
// }
//
//
//
// $('#selectCusID').change(function() {
//     let id = $(this).val();
//
//     for(let i = 0; i < allCustomers.length; i++){
//         if(allCustomers[i].id == id){
//             $('#orderCustomerID').val(allCustomers[i].id);
//             $('#orderCustomerName').val(allCustomers[i].name);
//             $('#orderCustomerAddress').val(allCustomers[i].address);
//             $('#orderCustomerSalary').val(allCustomers[i].salary);
//         }
//     }
// });
//
//
//
// $('#selectItemCode').change(function() {
//     let code = $(this).val();
//
//     for(let i = 0; i < allItems.length; i++){
//         if(allItems[i].code == code){
//             $('#txtItemCode').val(allItems[i].code)
//             $('#txtItemDescription').val(allItems[i].name)
//             $('#txtItemPrice').val(allItems[i].price)
//             $('#txtQTYOnHand').val(allItems[i].qty)
//         }
//     }
// });
//
//
//
// $("#btnAddToTable").click(function () {
//
//     let code = $("#selectItemCode").val();
//     let description = $("#txtItemDescription").val();
//     let itemPrice = $("#txtItemPrice").val();
//     let buyQty = $("#txtQty").val();
//     let avQty = $("#txtQTYOnHand").val();
//     let total = parseFloat(itemPrice) * parseFloat(buyQty);
//     $("#orderTable").append(`<tr><td>${code}</td><td>${description}</td><td>${itemPrice}</td><td>${avQty}</td><td>${buyQty}</td><td>${total}</td></tr>`);
//
//     let tot = Number($('#txtCash').val()) + total;
//     let subTot = Number($('#txtCash').val()) + total - Number($('#txtDiscount').val());
//
//     $('#total').text(tot);
//     $('#subtotal').text(subTot);
//
// });
//
//
//
// $("#txtDiscount").on("change paste keyup", function() {
//
//     $("#subtotal").text(parseInt($('#total').text()) - parseInt($("#txtDiscount").val()));
//
//     if(parseInt($("#subtotal").text()) < 0){
//         $("#subtotal").text("0");
//     }
//
//     $("#txtBalance").val(parseInt($('#txtCash').val()) - parseInt($("#subtotal").text()));
//
//     if(parseInt($("#txtBalance").val()) < 0){
//         $("#txtBalance").val("0");
//     }
//
// });
//
//
// $("#txtCash").on("change paste keyup", function() {
//
//     $("#txtBalance").val(parseInt($('#txtCash').val()) - parseInt($("#subtotal").text()));
//
//     if(parseInt($("#txtBalance").val()) < 0){
//         $("#txtBalance").val("0");
//     }
//
//     $("#subtotal").text(parseInt($('#total').text()) - parseInt($("#txtDiscount").val()));
//
//     if(parseInt($("#subtotal").text()) < 0){
//         $("#subtotal").text("0");
//     }
//
// });
//
//
// $('#btnSubmitOrder').click(function(){
//
//     let orderId = $('#txtOrderID').val();
//     let date = $('#txtDate').val();
//     let cusId = $('#orderCustomerID').val();
//     let itemD = getItemDetails();
//
//     let allData = {
//         orderId : orderId,
//         date : date,
//         cusId : cusId,
//         itemDet : itemD
//     }
//
//     $.ajax({
//         url: "http://localhost:8080/app/orders",
//         method: "post",
//         dataType: "json",
//         data: JSON.stringify(allData),
//         contentType: "application/json",
//         success: function (resp) {
//
//         },
//         error: function (error) {
//
//         }
//     });
//
// });



loadCustomerIDs();
loadItemIds();

function loadCustomerIDs() {
    var cusOption='';

    $.ajax({
        url:'http://localhost:8080/app/customers',
        method:'get',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success:function (customers) {
            for (i in customers) {
                let cus=customers[i];
                let id=cus.id;
                cusOption += '<option value="' +id + '">' + id + '</option>';
            }
            $("#selectCusID").append(cusOption);
        },
        error:function (res) {
            console.log(res);
        }
    });
}

$("#selectCusID").change(function () {
    let val=$(this).val();

    $.ajax({
        url:'http://localhost:8080/app/customers',
        method:'get',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success:function (customers) {
            for(i in customers){
                let id=customers[i].id;
                let name=customers[i].name;
                let salary=customers[i].salary;
                let adderss=customers[i].address;

                if(val==customers[i].id){
                    $('#orderCustomerID').val(id);
                    $('#orderCustomerName').val(name);
                    $('#orderCustomerSalary').val(salary);
                    $('#orderCustomerAddress').val(adderss);
                    break;
                }
            }
        },error:function (res) {
            console.log(res);
        }
    });
});

function loadItemIds() {
    var itemOption='';
    $.ajax({
        url:'http://localhost:8080/app/items',
        method:'get',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success:function (items) {
            for(i in items){
                let id=items[i].itemId;
                itemOption += '<option value="' +id + '">' + id + '</option>';
            }
            $('#selectItemCode').append(itemOption);
        }
    });
}

$('#selectItemCode').change(function () {
    var val=$(this).val();

    $.ajax({
        url:'http://localhost:8080/app/items',
        method:'get',
        headers:{
            Auth:"user=admin,pass=admin"
        },
        success:function (items) {
            for(i in items){

                if (val==items[i].itemId){
                    $('#txtItemCode').val(items[i].itemId);
                    $('#txtItemDescription').val(items[i].itemDes);
                    $('#txtItemPrice').val(items[i].itemUp);
                    $('#txtQTYOnHand').val(items[i].itemQty);
                    break;
                }
            }
        }
    });
});

let cartItems=[];
let subTotal=0;
let discount=0;
let finalTotal=0;

$('#btnAddToTable').click(function () {

    let itemCode=$('#txtItemCode').val();
    let itemName=$('#txtItemDescription').val();
    let price=$('#txtItemPrice').val();
    let qty=$('#txtQty').val();
    let qtyOnHand=$('#txtQTYOnHand').val();
    let total=price*qty;
    let itemCartRow=[];
    itemCartRow.push(itemCode,itemName,price,qty,total);

    let row=`<tr><td>${itemCode}</td><td>${itemName}</td><td>${price}</td><td>${qtyOnHand}</td><td>${qty}</td><td>${total}</td></tr>`;
    $("#orderTable").append(row);

    // cartItems.push(itemCartRow);

    for (let i = 0; i <=itemCartRow.length; i++) {
        subTotal=subTotal+itemCartRow[4];
        console.log(itemCartRow[4]);
        $('#txtTotal').val(parseInt(subTotal));
    }

    $('#txtQty').val("");
});

$('#txtDiscount').keydown(function (event) {

    if (event.key==="Enter"){
        if ($('#txtDiscount').val()!=="0"){
            finalTotal=subTotal-Number($('#txtDiscount').val());

            $('#txtSubTotal').val(finalTotal);
        }
    }

})

