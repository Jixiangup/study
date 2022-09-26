window.addEventListener('DOMContentLoaded', () => {
    // 获取左右元素
    const divs = document.querySelectorAll('#root div');
    const left = document.querySelector('#left');
    const right = document.querySelector('#right');

    for (const div of divs) {
        div.addEventListener('mouseenter', (event) => {
            event.target.style.witdh = '75%';
            event.target.id  == 'left' ? right.style.witdh = '25%' : left.style.window = '25%';
        })
    }
})