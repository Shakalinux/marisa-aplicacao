function showLoading() {
    var loadingModal = new
    bootstrap.Modal(document.getElementById('loadingModal'));
    loadingModal.show();


    setTimeout(function() {
        document.querySelector('form').submit();
    }, 2000);

    return false;
}


function confirmDelete(name, url) {
    document.getElementById('modalMessage').innerText = `Por favor, aguarde enquanto estamos excluindo o ${name}.`;
    const modal = new bootstrap.Modal(document.getElementById('loadingModal'));
    modal.show();
    setTimeout(() => {
        window.location.href = url;
    }, 2000);
}