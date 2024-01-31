let allCustomers;
let allItems;

loadAllCusDet();
loadAllItemDet();

$('#txtCash').val("0");
$('#txtDiscount').val("0");
$('#txtBalance').val("0");

function loadAllCusDet(){
    $.ajax({
        url : "http://localhost:8080/app/placeOrder",
        success : function(res){
            allCustomers = $(res);

            let optionCus;

            for(let i = 0; i < allCustomers.length; i++){
                let id = allCustomers[i].id;
                optionCus += '<option value="' + id + '">' + id + '</option>';
            }

            $('#selectCusID').append(optionCus);
        }
    });
}

function loadAllItemDet(){
    $.ajax({
        url : "http://localhost:8080/app/placeOrder",
        success : function(res){
            allItems = $(res);

            let optionItem;

            for(let i = 0; i < allItems.length; i++){
                let code = allItems[i].code;
                optionItem += '<option value="' + code + '">' + code + '</option>';
            }

            $('#selectItemCode').append(optionItem);
        }
    });
}

function getItemDetails() {
    let rows = $("#orderTable").children().length;
    var cart = [];

    for (let i = 0; i < rows; i++) {
        let itCode = $("#orderTable").children().eq(i).children(":eq(0)").text();
        let avQty = $("#orderTable").children().eq(i).children(":eq(3)").text();
        let itQty = $("#orderTable").children().eq(i).children(":eq(4)").text();
        let itPrice = $("#orderTable").children().eq(i).children(":eq(2)").text();
        cart.push({code: itCode, avQty:avQty, qty: itQty, price: itPrice});
    }

    return cart;
}

$('#selectCusID').change(function() {
    let id = $(this).val();

    for(let i = 0; i < allCustomers.length; i++){
        if(allCustomers[i].id == id){
            $('#orderCustomerID').val(allCustomers[i].id);
            $('#orderCustomerName').val(allCustomers[i].name);
            $('#orderCustomerAddress').val(allCustomers[i].address);
            $('#orderCustomerSalary').val(allCustomers[i].salary);
        }
    }
});