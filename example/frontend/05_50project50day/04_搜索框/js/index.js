/**
 * @author bnyte
 * @since
 */
window.addEventListener('DOMContentLoaded', () => {
    const root = document.getElementById('root');
    const goSearch = document.getElementById('go-search');

    let enable = root.classList.contains('active');
    goSearch.addEventListener('click', () => {
        enable = root.classList.contains('active');
        if (enable) {
            root.classList.remove('active');
        } else {
            root.classList.add('active');
        }
    })
})