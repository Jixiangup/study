window.addEventListener('load', () => {
    const show = document.getElementById('show');
    const close = document.getElementById('close');
    const loginWrapper = document.querySelector('.login-wrapper');
    const form = document.querySelector('form');
    const body = document.body;
    show.addEventListener('click', () => {
        loginWrapper.style.display = 'block';
        body.classList.add('show');
        
    })

    close.addEventListener('click', () => {
        loginWrapper.style.display = 'none';
        body.classList.remove('show');
        
    })

    form.addEventListener('mouseenter', () => {
        form.style.cursor = 'move'
    })

    let mouseToElementX, mouseToElementY;
    form.addEventListener('mousedown', (event) => {
        mouseToElementX = event.pageX - form.offsetLeft;
        mouseToElementY = event.pageY -  form.offsetTop;
        document.addEventListener('mousemove', (event) => {
            form.style.left =  event.pageX - mouseToElementX + "px"
            form.style.top =  event.pageY - mouseToElementY + "px"
        })
    })




})