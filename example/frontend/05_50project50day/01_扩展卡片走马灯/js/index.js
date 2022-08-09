window.onload = function() {

    // 图片容器类名
    const cardClassName = 'card';
    const activeClassName = 'active';
    const showRemarkClassName = 'show-remark';
    // 获取所有图片元素
    const cards = document.getElementsByClassName(cardClassName);
    const actives = document.getElementsByClassName(activeClassName);
    const showRemarks = document.getElementsByClassName(showRemarkClassName);
    for (const card of cards) {
        card.onclick = function() {
            carousel(card);
        }
    }

    // 切换当前选中显示
    const carousel = (card) => {
        for (const active of actives) {
            active.classList.remove(activeClassName);
        }
        for (const showRemark of showRemarks) {
            showRemark.classList.remove(showRemarkClassName);
        }
        card.classList.add(activeClassName);
        card.getElementsByTagName('h3')[0].classList.add(showRemarkClassName);
    }

    
}