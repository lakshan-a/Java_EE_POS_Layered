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
