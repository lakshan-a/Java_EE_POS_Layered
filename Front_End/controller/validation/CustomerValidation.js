const CUS_ID_REGEX = /^(C)[0-9]{3}$/;
const CUS_NAME_REGEX = /^[A-Za-z ]{5,}$/;
const CUS_ADDRESS_REGEX = /^[A-Za-z0-9 ]{5,}$/;
const CUS_SALARY_REGEX = /^[0-9]{2,}([.][0-9]{2})?$/;

var validationId;
var validationName;
var validationAddress;
var validationSalary;

// let customerNumberCounter = 1;
// let customerIDstor;
//
// generateID();
// function generateID() {
//     const customerID = `C${String(customerNumberCounter).padStart(3, '0')}`;
//     customerNumberCounter++;
//     customerIDstor = customerID;
//     $("#txtCustomerID").val(customerID);
// }

function clearCustomerInputFields() {
    $("#txtCustomerID,#txtCustomerName,#txtCustomerAddress,#txtCustomerSalary").val("");
    $("#txtCustomerID,#txtCustomerName,#txtCustomerAddress,#txtCustomerSalary").css("border", "1px solid #ced4da");
    $("#txtCustomerID").focus();
}


$("#btnCusAdd").attr('disabled',true);
$("#btnCusUpdate").attr('disabled',true);

$("#txtCustomerID").keyup(function (e) {
    let value = $("#txtCustomerID").val();
    if (value.length == 1) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerID").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ID_REGEX.test(value);
        if (res) {
            validationId =1;
            setBtn();
            $("#txtCustomerID").css('border', '2px solid green');
        } else {
            $("#txtCustomerID").css('border', '2px solid red');
        }
    }});

$("#txtCustomerName").keyup(function (e) {
    let value = $("#txtCustomerName").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerName").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validationName=1;
            setBtn();
            $("#txtCustomerName").css('border', '2px solid green');
        } else {
            $("#txtCustomerName").css('border', '2px solid red');
        }
    }});

$("#txtCustomerAddress").keyup(function (e) {
    let value = $("#txtCustomerAddress").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerAddress").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ADDRESS_REGEX.test(value);
        if (res) {
            validationAddress=1;
            setBtn();
            $("#txtCustomerAddress").css('border', '2px solid green');
        } else {
            $("#txtCustomerAddress").css('border', '2px solid red');
        }
    }});

$("#txtCustomerSalary").keyup(function (e) {
    let value = $("#txtCustomerSalary").val();
    if (value.length == 0) {
        $("#btnCusAdd").attr('disabled',true);
        $("#txtCustomerSalary").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validationSalary=1;
            setBtn();
            $("#btnCusAdd").attr('disabled',false);
            $("#btnCusUpdate").attr('disabled',false);
            $("#txtCustomerSalary").css('border', '2px solid green');
        } else {
            $("#txtCustomerSalary").css('border', '2px solid red');
        }
    }});


function setBtn() {
    if (validationId==1 && validationName==1 && validationAddress==1 && validationSalary==1){
        $("#btnCusAdd").attr('disabled',false);
        $("#btnCusUpdate").attr('disabled',false);
    }
}

// function saveUpdateAlert(vale, value2) {
//     Swal.fire({
//         position: 'top-end',
//         icon: 'success',
//         title: vale + ' has been ' + value2,
//         showConfirmButton: false,
//         timer: 2500
//     });
// }