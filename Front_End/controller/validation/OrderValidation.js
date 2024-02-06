/*
const ORDER_ID_REGEX = /^(O)[0-9]{3}$/;
const ORDER_DATE_REGEX = /^\d{4}-\d{2}-\d{2}$/;
const numbersOnlyRegex = /^[0-9]+$/;
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;
const CUS_ID_REGEX = /^(C)[0-9]{3}$/;
const ITEM_ID_REGEX = /^(P)[0-9]{3}$/;


$("#btnAddToTable").attr('disabled',true);
$("#btnSubmitOrder").attr('disabled',true);

var validation1;
var validation2;
var validation3;
var validation4;
var validation5;
var validation6;
var validation7;
var validation8;
var validation9;
var validation10;
var validation11;



$("#txtOrderID").keyup(function (e) {
    let value = $("#txtOrderID").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#txtOrderID").css('border', '1px solid #ced4da');
    } else {
        let res = ORDER_ID_REGEX.test(value);
        if (res) {
            setBtn();
            $("#txtOrderID").css('border', '2px solid green');
            validation1 = 1;
        } else {
            $("#txtOrderID").css('border', '2px solid red');
        }
    }
});

// $("#txtDate").on('input', function (e) {
//     let value = $("#txtDate").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtDate").css('border', '1px solid #ced4da');
//     } else {
//         let res = ORDER_DATE_REGEX.test(value);
//         if (res) {
//             validation2 = 1;
//             setBtn();
//             $("#txtDate").css('border', '2px solid green');
//         } else {
//             $("#txtDate").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#orderCustomerName").keyup(function (e) {
//     let value = $("#orderCustomerName").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#orderCustomerName").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_NAME_REGEX.test(value);
//         if (res) {
//             validation3 = 1;
//             setBtn();
//             $("#orderCustomerName").css('border', '2px solid green');
//         } else {
//             $("#orderCustomerName").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#orderCustomerAddress").keyup(function (e) {
//     let value = $("#orderCustomerAddress").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#orderCustomerAddress").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_NAME_REGEX.test(value);
//         if (res) {
//             validation4 = 1;
//             setBtn();
//             $("#orderCustomerAddress").css('border', '2px solid green');
//         } else {
//             $("#orderCustomerAddress").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#orderCustomerSalary").keyup(function (e) {
//     let value = $("#orderCustomerSalary").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#orderCustomerSalary").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_SALARY_REGEX.test(value);
//         if (res) {
//             validation5 = 1;
//             setBtn();
//             $("#orderCustomerSalary").css('border', '2px solid green');
//         } else {
//             $("#orderCustomerSalary").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#orderCustomerID").keyup(function (e) {
//     let value = $("#orderCustomerID").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#orderCustomerID").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_ID_REGEX.test(value);
//         if (res) {
//             validation6 = 1;
//             setBtn();
//             $("#orderCustomerID").css('border', '2px solid green');
//         } else {
//             $("#orderCustomerID").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtItemCode").on("input", function (e) {
//     let value = $("#txtItemCode").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtItemCode").css('border', '1px solid #ced4da');
//     } else {
//         let res = ITEM_ID_REGEX.test(value);
//         if (res) {
//             validation7 = 1;
//             setBtn();
//             $("#txtItemCode").css('border', '2px solid green');
//         } else {
//             $("#txtItemCode").css('border', '2px solid red');
//         }
//     }
// });
//
//
// $("#txtItemPrice").on("input", function (e) {
//     let value = $("#txtItemPrice").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtItemPrice").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_SALARY_REGEX.test(value);
//         if (res) {
//             validation8 = 1;
//             setBtn();
//             $("#txtItemPrice").css('border', '2px solid green');
//         } else {
//             $("#txtItemPrice").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtQTYOnHand").keyup(function (e) {
//     let value = $("#txtQTYOnHand").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtQTYOnHand").css('border', '1px solid #ced4da');
//     } else {
//         let res = numbersOnlyRegex.test(value);
//         if (res) {
//             validation9 = 1;
//             setBtn();
//             $("#txtQTYOnHand").css('border', '2px solid green');
//         } else {
//             $("#txtQTYOnHand").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtQty").keyup(function (e) {
//     let value = $("#txtQty").val();
//
//     // Reset border and validation
//     $("#txtQty").css('border', '1px solid #ced4da');
//     validation10 = 0;
//
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//     } else {
//         let res = numbersOnlyRegex.test(value);
//         if (res) {
//             let oQty = parseInt(value);
//             let iQty = parseInt($("#txtQty").val());
//
//             if (!isNaN(iQty) && oQty <= iQty) {
//                 validation10 = 1;
//                 setBtn();
//                 $("#txtQty").css('border', '2px solid green');
//             } else {
//                 $("#txtQty").css('border', '2px solid red');
//             }
//         } else {
//             $("#txtQty").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtItemDescription").keyup(function (e) {
//     let value = $("#txtItemDescription").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtItemDescription").css('border', '1px solid #ced4da');
//     } else {
//         let res = CUS_NAME_REGEX.test(value);
//         if (res) {
//             validation11 = 1;
//             setBtn();
//             $("#txtItemDescription").css('border', '2px solid green');
//         } else {
//             $("#txtItemDescription").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtCash").on("input", function (e) {
//     balanceCheck();
//     let value = $("#txtCash").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtCash").css('border', '1px solid #ced4da');
//     } else {
//         let res = numbersOnlyRegex.test(value);
//         if (res) {
//             setBtn();
//             $("#txtCash").css('border', '2px solid green');
//         } else {
//             $("#txtCash").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtDiscount").keyup(function (e) {
//     let value = $("#txtDiscount").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtDiscount").css('border', '1px solid #ced4da');
//     } else {
//         let res = numbersOnlyRegex.test(value);
//         if (res) {
//             setBtn();
//             $("#txtDiscount").css('border', '2px solid green');
//         } else {
//             $("#txtDiscount").css('border', '2px solid red');
//         }
//     }
// });
//
// $("#txtBalance").keyup(function (e) {
//     let value = $("#txtBalance").val();
//     if (value.length == 0) {
//         $("#btnAddToTable").attr('disabled', true);
//         $("#btnSubmitOrder").attr('disabled', true);
//         $("#txtBalance").css('border', '1px solid #ced4da');
//     } else {
//         let res = numbersOnlyRegex.test(value);
//         if (res) {
//             if ($("#txtBalance").val()==0){
//                 $("#txtBalance").css('border', '2px solid green');
//             }else {
//                 $("#txtBalance").css('border', '2px solid red');
//             }
//             setBtn();
//         } else {
//             $("#txtBalance").css('border', '2px solid red');
//         }
//     }
// });
//
function setBtn() {
    if (validation1 == 1 && validation2 == 1 && validation3 == 1 && validation4 == 1 && validation5 == 1 && validation6 == 1 && validation7 == 1 && validation8 == 1 && validation9 == 1 && validation10 == 1 && validation11 == 1) {
        $("#btnAddToTable").attr('disabled', false);
        $("#btnSubmitOrder").attr('disabled', false);
    }
}
//
//
// function balanceCheck() {
//
//     var balance = parseInt($("#orderBalanceTxt").val());
//     if (balance===0){
//         $("#orderBalanceTxt").css('border', '2px solid green');
//     }else {
//         $("#orderBalanceTxt").css('border', '2px solid red');
//     }
//
// }


*/

const ORDER_ID_REGEX = /^(D)[0-9]{2}$/;


var validationId;



// $("#btnCusAdd").attr('disabled',true);
// $("#btnCusUpdate").attr('disabled',true);


$("#btnAddToTable").attr('disabled',true);
$("#btnSubmitOrder").attr('disabled',true);

$("#txtOrderID").keyup(function (e) {
    let value = $("#txtOrderID").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtOrderID").css('border', '1px solid #ced4da');
    } else {
        let res = ORDER_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#txtOrderID").css('border', '2px solid green');
        } else {
            $("#txtOrderID").css('border', '2px solid red');
        }
    }});

function setBtn() {
    if (validationId==1 ){
        $("#btnAddToTable").attr('disabled',false);
        $("#btnSubmitOrder").attr('disabled',false);
    }
}

