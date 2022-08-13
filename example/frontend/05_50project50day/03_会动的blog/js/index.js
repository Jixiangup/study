window.addEventListener('DOMContentLoaded', () => {
    const pageChange = document.getElementById('page-change');
    const root = document.getElementById('root');
    const buts = document.getElementsByTagName('button');
    const navigationContainer = document.querySelector('.navigation-container');
    let show = root.classList.contains('show');

    const showOrClose = () => {
        // debugger
        if (show) {
            root.classList.add('show')
            pageChange.classList.add('open')
            pageChange.style.transform = 'rotate(90deg)'
            for (const but of buts) {
                but.style.transform = 'rotate(-90deg)'
            }
            navigationContainer.style.left = 0
        } else {
            pageChange.classList.remove('open')
            root.classList.remove('show')
            pageChange.style.transform = 'rotate(0deg)'
            for (const but of buts) {
                but.style.transform = 'rotate(0deg)'
            }
            navigationContainer.style.left = '-200px'
        }   
    }

    /*
    .open {
    transform: rotate(90deg);
    button {
        transform: rotate(-90deg);
    }
}
    */

    showOrClose();
    pageChange.addEventListener('click', (event) => {
        show = !show;
        showOrClose();
    })




})