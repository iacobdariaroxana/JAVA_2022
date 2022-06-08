function inbox() {
    let user = parseJwt(window.localStorage.getItem("token"));
    console.log(user);
    fetch("http://localhost:2022/messages/userinbox/" + user.id, {
        method: "GET",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        }
    }).then(async res => {
        let messages = (await res.text()).split("###");
        console.log(messages);
        for (let i = 0; i < messages.length; ++i) {
            const newRow = document.createElement("tr");
            const newColumn1 = document.createElement("td");
            newColumn1.innerText = JSON.parse(messages[i]).from;
            const newColumn2 = document.createElement("td");
            newColumn2.innerText = JSON.parse(messages[i]).message;
            newRow.append(newColumn1);
            newRow.append(newColumn2);
            document.getElementById("inboxTable").querySelector('tbody').appendChild(newRow);
        }
        document.getElementById("inboxTable")
            .querySelector('thead').querySelector('tr')
            .querySelector('th').innerText = "From";
    }).catch(err => {
        console.log(err);
    })
}