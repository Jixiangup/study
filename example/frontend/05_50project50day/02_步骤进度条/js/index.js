window.onload = () => {
    const stepClassName = 'step';
    const progressId = 'progress';

    const steps = document.getElementsByClassName(stepClassName);

    let stepNumber = 1;
    for (const step of steps) {
        step.innerHTML = stepNumber++;
    }

    const setProgressWitdh = () => {
        const progress = document.getElementById(progressId);
        progress.setAttribute('witdh', steps.length * 100 + 'px');
        progress.setAttribute('height', '3px');
        progress.style.backgroundColor = '#dcdcdc'
    }

    setProgressWitdh();
}