window.onload = () => {
    const stepClassName = 'step';
    const progressId = 'progress';
    
    const progress = document.getElementById(progressId);
    const prev = document.getElementById('prev');
    const next = document.getElementById('next');
    const actives = document.getElementsByClassName('active');
    const steps = document.getElementsByClassName(stepClassName);
    
    progress.style.width = ((actives.length - 1) / steps.length) * 100  + "%";
    const operateButs = document.getElementsByClassName('operate');

    if (actives.length > 1) {
        prev.disabled = false;
    }

    if (actives.length === steps.length) {
        next.disabled = true;
    }

    prev.onclick = () => {
        for (let i = 0; i < actives.length; i++) {
            if (i === actives.length - 1) {
                actives[i].classList.remove('active');
            }
        }
        if (actives.length === 1) {
            prev.disabled = true;
        }
        if (actives.length > 1) {
            next.disabled = false;
        }
        progress.style.width = ((actives.length - 1) / steps.length) * 100  + "%";
    }

    next.onclick = () => {
        if (actives.length === steps.length - 1) {
            next.disabled = true;
        } else if (actives.length === steps.length) {
            return;
        } else {
            prev.disabled = false;
        }
        
        steps[actives.length].classList.add('active');
        progress.style.width = ((actives.length - 1) / steps.length) * 100  + "%";
    }

}