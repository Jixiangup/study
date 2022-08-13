/**
 * @author bnyte
 * @since
 */
window.addEventListener('DOMContentLoaded', () => {
    const defaultBlur = 30
    let load = 0;
    const bg = document.getElementById('bg');
    const loadingText = document.getElementById('loading-text');

    let resetBlur= setInterval(() => {
        load++;
        if (load > 99) {
            clearInterval(resetBlur);
        }
        loadingText.innerHTML = `${load}%`
        loadingText.style.opacity = scale(load, 0, 100, 1, 0);
        bg.style.filter = `blur(${scale(load, 0, 100, 30, 0)}px)`
    }, 30)

    const scale = (num, in_min, in_max, out_min, out_max) => {
        return ((num - in_min) * (out_max - out_min)) / (in_max - in_min) + out_min
    }
})