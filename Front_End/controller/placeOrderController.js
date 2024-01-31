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