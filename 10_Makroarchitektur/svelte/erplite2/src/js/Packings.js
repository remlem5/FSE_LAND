
let packingsByOrder, packingItemList, id;

export async function getPackingsByOrderId(orderId) {
    const response = await fetch("http://localhost:8080/packings/search/findByOrderId?orderId="+ orderId, {method: 'GET'});
    packingsByOrder = await response.json();
    const response2 = await fetch(packingsByOrder._links.packingItemList.href);
    packingItemList = await response2.json();
    return packingItemList._embedded.packingItems
}

export async function itemPacked(item) {
    let link = JSON.stringify(item._links.packingItem)
    if (link.length == 47){
        id = link.charAt(link.length - 3);
    } else {
        id = link.charAt(link.length - 4) + link.charAt(link.length - 3)
    }
    let url = "http://localhost:8080/stock/setPackedForPacking/" + id;
    //postMessage("http://localhost:8080/stock/setPackedForPacking/" + id);
    const post = await fetch(url, {method: 'POST'});
    window.location.reload();
    console.log("URL",url)
    console.log(link.length)
}

