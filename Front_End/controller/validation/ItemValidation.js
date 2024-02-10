const ITEM_ID_REGEX = /^(P)[0-9]{3}$/;
const ITEM_NAME_REGEX = /^[A-Za-z ]{4,}$/;
const numbersOnlyRegex = /^[0-9]+$/;
const ITEM_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationPrice;
var validationQTY;

// let itemNumberCounter = 1;
// let itemIDstor;
//
// generateItemID();
// function generateItemID() {
//     const itemCode = `P${String(itemNumberCounter).padStart(3, '0')}`;
//     itemNumberCounter++;
//     itemIDstor = itemCode;
//     $("#itemCode").val(itemCode);
// }

function clearItemInputFields() {
    $("#itemCode,#itemName,#itemPrice,#itemQty").val("");
    $("#itemCode,#itemName,#itemPrice,#itemQty").css("border", "1px solid #ced4da");
    $("#itemCode").focus();
}


$("#btnItemAdd").attr('disabled',true);
$("#btnItemUpdate").attr('disabled',true);

$("#itemCode").keyup(function (e) {
    let value = $("#itemCode").val();
    if (value.length == 1) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemCode").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#itemCode").css('border', '2px solid green');
        } else {
            $("#itemCode").css('border', '2px solid red');
        }
    }});

$("#itemName").keyup(function (e) {
    let value = $("#itemName").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemName").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#itemName").css('border', '2px solid green');
        } else {
            $("#itemName").css('border', '2px solid red');
        }
    }});

$("#itemPrice").keyup(function (e) {
    let value = $("#itemPrice").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemPrice").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_SALARY_REGEX.test(value);
        if (res) {
            validationPrice=1;
            setBtn();
            $("#itemPrice").css('border', '2px solid green');
        } else {
            $("#itemPrice").css('border', '2px solid red');
        }
    }});

$("#itemQty").keyup(function (e) {
    let value = $("#itemQty").val();
    if (value.length == 0) {
        $("#btnItemAdd").attr('disabled',true);
        $("#itemQty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validationQTY=1;
            setBtn();
            $("#itemQty").css('border', '2px solid green');
        } else {
            $("#itemQty").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationPrice==1 && validationQTY==1){
        $("#btnItemAdd").attr('disabled',false);
        $("#btnItemUpdate").attr('disabled',false);
    }
}
