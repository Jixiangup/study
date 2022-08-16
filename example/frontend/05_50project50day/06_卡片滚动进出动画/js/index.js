window.addEventListener('DOMContentLoaded', () => {
    // 获取所有卡片
    const cards = document.getElementsByClassName('card');

    const checkCard = () => {
        // 容纳所有元素的高度
        const triggerBottom = window.innerHeight / 5 * 4;
        for (const card of cards) {
            // 获取当前元素相对于视口的位置 这里获取到的是相对于顶部
            const cardTop = card.getBoundingClientRect().top;
            if (cardTop < triggerBottom) {
                card.classList.add('show');
            } else {
                card.classList.remove('show');
            }
        }

    }

    window.addEventListener('scroll', () => {
        checkCard();   
    })

    checkCard();


})