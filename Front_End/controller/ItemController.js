loadAllItems();

function loadAllItems(){

    $.ajax({
        url : "http://localhost:8080/app/items",
        success : function(res){
            let item = $(res);
            $('#tblItem').empty();

            for(let i = 0; i < item.length; i++){
                let code = item[i].code;
                let name = item[i].name;
                let qty = item[i].qty;
                let price = item[i].price;

                let row =`<tr><td>${code}</td><td>${name}</td><td>${qty}</td><td>${price}</td></tr>`;
                $('#tblItem').append(row);
            }
        }
    });

}

$('#btnItemGetAll').on("click",function () {
    Getall();
});

function Getall(){
    $.ajax({
        url : "http://localhost:8080/app/items",
        method : "GET",
        success : function (resp) {
            // console.log("Success: ", resp);
            console.log(resp[0])
            for (let i=0; i<resp.length; i++) {

                console.log(resp[i].code);
                console.log(resp[i].name);
                console.log(resp[i].qty);
                console.log(resp[i].price);

                const row = `<tr>
                                <td>${resp[i].code}</td>
                                <td>${resp[i].name}</td>
                                <td>${resp[i].qty}</td>
                                <td>${resp[i].price}</td>
                            </tr>`;
                $('#tblItem').append(row);
            }

        },
        error : function (error) {
            console.log("error: ", error);
        }
    })
}


$('#btnItemAdd').click(function () {


    const code = $('#itemCode').val();
    const name = $('#itemName').val();
    const qty = $('#itemQty').val();
    const price = $('#itemPrice').val();

    const itemObj = {
        code:code,
        name:name,
        qty:qty,
        price:price
    };

    const jsonObj = JSON.stringify(itemObj);

    $.ajax({
        url: "http://localhost:8080/app/items",
        method: "POST",
        data: {
            code:code,
            name:name,
            qty:qty,
            price:price
        },

        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
        },
        success : function() {
            loadAllCus();
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })
});

$('#btnItemDelete').click(function () {

    const code = $('#itemCode').val();

    $.ajax({
        url: "http://localhost:8080/app/items?code=" + code,
        method: "DELETE",
        success: function (resp, textStatus, jqxhr) {
            console.log("success: ", resp);
            console.log("success: ", textStatus);
            console.log("success: ", jqxhr);
        },
        success : function() {
            loadAllCus();
        },
        error: function (error) {
            console.log("error: ", error);
        }
    })


});
