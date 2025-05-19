document.addEventListener("DOMContentLoaded", () => {
    // 市集篩選功能
    const filterButtons = document.querySelectorAll(".filter-btn");
    const marketCards = document.querySelectorAll(".market-card");
  
    filterButtons.forEach(button => {
      button.addEventListener("click", () => {
        filterButtons.forEach(btn => btn.classList.remove("active"));
        button.classList.add("active");
  
        const filter = button.getAttribute("data-filter");
        marketCards.forEach(card => {
          card.style.display = (card.dataset.status === filter) ? "flex" : "none";
        });
      });
    });
  
    // 預設啟動「進行中」篩選
    const defaultFilter = document.querySelector(".filter-btn.active") || filterButtons[0];
    defaultFilter?.click();
  
    // 登入/註冊功能
    const loginLink = document.querySelector('.login-btn');
    const modal = document.getElementById('loginModal');
    const closeBtn = document.querySelector('.close-btn');
  
    loginLink.addEventListener('click', (e) => {
      e.preventDefault();
      modal.style.display = 'block';
    });
  
    closeBtn.addEventListener('click', () => {
      modal.style.display = 'none';
    });
  
    window.addEventListener('click', (e) => {
      if (e.target === modal) {
        modal.style.display = 'none';
      }
    });
  
    // 登入表單提交處理：依身份導向不同頁面
    document.getElementById('loginForm').addEventListener('submit', function (e) {
      e.preventDefault(); // 阻止表單預設送出行為
  
      const role = document.getElementById('role').value;
  
      if (!role) {
        alert("請選擇身份！");
        return;
      }
  
      switch (role) {
        case 'user':
          window.location.href = 'user.html';
          break;
        case 'vendor':
          window.location.href = 'vendor.html';
          break;
        case 'organizer':
          window.location.href = 'organizer.html';
          break;
        default:
          alert("未知身份，請重新選擇。");
      }
    });
  });

    // 註冊表單提交處理：依身份導向不同頁面
    document.addEventListener("DOMContentLoaded", () => {
      const form = document.getElementById("registerForm");
      
      if (form) {
        form.addEventListener("submit", function (e) {
          e.preventDefault(); // 阻止預設表單提交行為
      
          const role = document.getElementById("role").value;
      
          if (!role) {
            alert("請選擇身份！");
            return;
          }
  
            // 根據身份導向不同頁面
            switch (role) {
              case 'user':
                window.location.href = 'user.html';
                break;
              case 'vendor':
                window.location.href = 'vendor.html';
                break;
              case 'organizer':
                window.location.href = 'organizer.html';
                break;
              default:
                alert("未知身份，請重新選擇。");
            }
          });
        }
      });
  
    // 編輯個人資訊彈窗功能
    const openEditBtn = document.querySelector('.edit-trigger'); // 開啟用
    const editModal = document.getElementById('editModal');
    const closeEditBtn = document.querySelector('.edit-close-btn'); // 可選
    
    if (openEditBtn && editModal) {
      openEditBtn.addEventListener('click', () => {
        editModal.style.display = 'flex';
      });
    
      // 點擊 modal 背景關閉
      window.addEventListener('click', (e) => {
        if (e.target === editModal) {
          editModal.style.display = 'none';
        }
      });
    
      // 如果有 ✕ 按鈕也能關閉
      closeEditBtn?.addEventListener('click', () => {
        editModal.style.display = 'none';
      });
    }
    
   
      