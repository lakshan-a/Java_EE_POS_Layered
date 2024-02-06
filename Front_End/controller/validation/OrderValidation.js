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

$("#txtDate").on('input', function (e) {
    let value = $("#txtDate").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#txtDate").css('border', '1px solid #ced4da');
    } else {
        let res = ORDER_DATE_REGEX.test(value);
        if (res) {
            validation2 = 1;
            setBtn();
            $("#txtDate").css('border', '2px solid green');
        } else {
            $("#txtDate").css('border', '2px solid red');
        }
    }
});

$("#orderCustomerName").keyup(function (e) {
    let value = $("#orderCustomerName").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderCustomerName").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation3 = 1;
            setBtn();
            $("#orderCustomerName").css('border', '2px solid green');
        } else {
            $("#orderCustomerName").css('border', '2px solid red');
        }
    }
});

$("#orderCustomerAddress").keyup(function (e) {
    let value = $("#orderCustomerAddress").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderCustomerAddress").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation4 = 1;
            setBtn();
            $("#orderCustomerAddress").css('border', '2px solid green');
        } else {
            $("#orderCustomerAddress").css('border', '2px solid red');
        }
    }
});

$("#orderCustomerSalary").keyup(function (e) {
    let value = $("#orderCustomerSalary").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderCustomerSalary").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validation5 = 1;
            setBtn();
            $("#orderCustomerSalary").css('border', '2px solid green');
        } else {
            $("#orderCustomerSalary").css('border', '2px solid red');
        }
    }
});

$("#orderCustomerID").keyup(function (e) {
    let value = $("#orderCustomerID").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderCustomerID").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ID_REGEX.test(value);
        if (res) {
            validation6 = 1;
            setBtn();
            $("#orderCustomerID").css('border', '2px solid green');
        } else {
            $("#orderCustomerID").css('border', '2px solid red');
        }
    }
});

$("#txtItemCode").on("input", function (e) {
    let value = $("#txtItemCode").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#txtItemCode").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_ID_REGEX.test(value);
        if (res) {
            validation7 = 1;
            setBtn();
            $("#txtItemCode").css('border', '2px solid green');
        } else {
            $("#txtItemCode").css('border', '2px solid red');
        }
    }
});


$("#txtItemPrice").on("input", function (e) {
    let value = $("#txtItemPrice").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#txtItemPrice").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validation8 = 1;
            setBtn();
            $("#txtItemPrice").css('border', '2px solid green');
        } else {
            $("#txtItemPrice").css('border', '2px solid red');
        }
    }
});

$("#iOQty").keyup(function (e) {
    let value = $("#iOQty").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#iOQty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validation9 = 1;
            setBtn();
            $("#iOQty").css('border', '2px solid green');
        } else {
            $("#iOQty").css('border', '2px solid red');
        }
    }
});

$("#oqty").keyup(function (e) {
    let value = $("#oqty").val();

    // Reset border and validation
    $("#oqty").css('border', '1px solid #ced4da');
    validation10 = 0;

    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            let oQty = parseInt(value);
            let iQty = parseInt($("#iOQty").val());

            if (!isNaN(iQty) && oQty <= iQty) {
                validation10 = 1;
                setBtn();
                $("#oqty").css('border', '2px solid green');
            } else {
                $("#oqty").css('border', '2px solid red');
            }
        } else {
            $("#oqty").css('border', '2px solid red');
        }
    }
});

$("#ItemNameOrder").keyup(function (e) {
    let value = $("#ItemNameOrder").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#ItemNameOrder").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation11 = 1;
            setBtn();
            $("#ItemNameOrder").css('border', '2px solid green');
        } else {
            $("#ItemNameOrder").css('border', '2px solid red');
        }
    }
});

$("#orderCashTxt").on("input", function (e) {
    balanceCheck();
    let value = $("#orderCashTxt").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderCashTxt").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            setBtn();
            $("#orderCashTxt").css('border', '2px solid green');
        } else {
            $("#orderCashTxt").css('border', '2px solid red');
        }
    }
});

$("#orderDiscountTxt").keyup(function (e) {
    let value = $("#orderDiscountTxt").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderDiscountTxt").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            setBtn();
            $("#orderDiscountTxt").css('border', '2px solid green');
        } else {
            $("#orderDiscountTxt").css('border', '2px solid red');
        }
    }
});

$("#orderBalanceTxt").keyup(function (e) {
    let value = $("#orderBalanceTxt").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#orderBalanceTxt").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            if ($("#orderBalanceTxt").val()==0){
                $("#orderBalanceTxt").css('border', '2px solid green');
            }else {
                $("#orderBalanceTxt").css('border', '2px solid red');
            }
            setBtn();
        } else {
            $("#orderBalanceTxt").css('border', '2px solid red');
        }
    }
});

function setBtn() {
    if (validation1 == 1 && validation2 == 1 && validation3 == 1 && validation4 == 1 && validation5 == 1 && validation6 == 1 && validation7 == 1 && validation8 == 1 && validation9 == 1 && validation10 == 1 && validation11 == 1) {
        $("#btnAddToTable").attr('disabled', false);
        $("#btnSubmitOrder").attr('disabled', false);
    }
}


/*export function validated1() {
    let value = $("#oId").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#txtOrderID").css('border', '1px solid #ced4da');
    } else {
        let res = ORDER_ID_REGEX.test(value);
        if (res) {
            setBtn();
            $("#oId").css('border', '2px solid green');
            validation1 = 1;
        } else {
            $("#oId").css('border', '2px solid red');
        }
    }
}
export function validated2() {
    let value = $("#date").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#date").css('border', '1px solid #ced4da');
    } else {
        let res = ORDER_DATE_REGEX.test(value);
        if (res) {
            validation2 = 1;
            setBtn();
            $("#date").css('border', '2px solid green');
        } else {
            $("#date").css('border', '2px solid red');
        }
    }
}
export function validated3() {
    let value = $("#oCName").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#oCName").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation3 = 1;
            setBtn();
            $("#oCName").css('border', '2px solid green');
        } else {
            $("#oCName").css('border', '2px solid red');
        }
    }
}
export function validated4() {
    let value = $("#oCAddress").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#oCAddress").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation4 = 1;
            setBtn();
            $("#oCAddress").css('border', '2px solid green');
        } else {
            $("#oCAddress").css('border', '2px solid red');
        }
    }

}

export function validated5() {
    let value = $("#oCSalary").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#oCSalary").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validation5 = 1;
            setBtn();
            $("#oCSalary").css('border', '2px solid green');
        } else {
            $("#oCSalary").css('border', '2px solid red');
        }
    }
}
export function validated6() {
    let value = $("#CustomerIDORderForm").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#CustomerIDORderForm").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_ID_REGEX.test(value);
        if (res) {
            validation6 = 1;
            setBtn();
            $("#CustomerIDORderForm").css('border', '2px solid green');
        } else {
            $("#CustomerIDORderForm").css('border', '2px solid red');
        }
    }
}
export function validated7() {
    let value = $("#itemID").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#itemID").css('border', '1px solid #ced4da');
    } else {
        let res = ITEM_ID_REGEX.test(value);
        if (res) {
            validation7 = 1;
            setBtn();
            $("#itemID").css('border', '2px solid green');
        } else {
            $("#itemID").css('border', '2px solid red');
        }
    }
}
export function validated8() {
    let value = $("#iOPrice").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#iOPrice").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_SALARY_REGEX.test(value);
        if (res) {
            validation8 = 1;
            setBtn();
            $("#iOPrice").css('border', '2px solid green');
        } else {
            $("#iOPrice").css('border', '2px solid red');
        }
    }
}
export function validated9() {
    let value = $("#iOQty").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#iOQty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            validation9 = 1;
            setBtn();
            $("#iOQty").css('border', '2px solid green');
        } else {
            $("#iOQty").css('border', '2px solid red');
        }
    }
}
export function validated10() {
    let value = $("#oqty").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#oqty").css('border', '1px solid #ced4da');
    } else {
        let res = numbersOnlyRegex.test(value);
        if (res) {
            if (parseInt($("#iOQty").val()) >= parseInt($("#oqty").val())) {
                validation10 = 1;
                setBtn();
                $("#oqty").css('border', '2px solid green');
            } else {
                $("#oqty").css('border', '2px solid red');
            }
        } else {
            $("#oqty").css('border', '2px solid red');
        }
    }
}
export function validated11() {
    let value = $("#ItemNameOrder").val();
    if (value.length == 0) {
        $("#btnAddToTable").attr('disabled', true);
        $("#btnSubmitOrder").attr('disabled', true);
        $("#ItemNameOrder").css('border', '1px solid #ced4da');
    } else {
        let res = CUS_NAME_REGEX.test(value);
        if (res) {
            validation11 = 1;
            setBtn();
            $("#ItemNameOrder").css('border', '2px solid green');
        } else {
            $("#ItemNameOrder").css('border', '2px solid red');
        }
    }
}*/


function balanceCheck() {

    var balance = parseInt($("#orderBalanceTxt").val());
    if (balance===0){
        $("#orderBalanceTxt").css('border', '2px solid green');
    }else {
        $("#orderBalanceTxt").css('border', '2px solid red');
    }

}


