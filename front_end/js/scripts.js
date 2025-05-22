////----- index.html 頁面功能 ----- ////
  // 市集篩選功能
    document.addEventListener("DOMContentLoaded", () => {
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
    
    ////----- register.html 頁面功能 ----- ////
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

    ////----- user_basic_info.html & 共用 header 頁面功能 ----- ////
    document.addEventListener("DOMContentLoaded", () => {
      // 編輯個人資訊彈窗功能
      const openEditBtn = document.querySelector('.edit-trigger');
      const editModal = document.getElementById('editModal');
      const closeEditBtn = document.querySelector('.edit-close-btn');
    
      if (openEditBtn && editModal) {
        openEditBtn.addEventListener('click', () => {
          editModal.style.display = 'flex';
        });
    
        window.addEventListener('click', (e) => {
          if (e.target === editModal) {
            editModal.style.display = 'none';
          }
        });
    
        closeEditBtn?.addEventListener('click', () => {
          editModal.style.display = 'none';
        });
      }
    
      // 基本資料 & 收藏清單切換標籤邏輯
      const tabInfo = document.getElementById("tab-info");
      const tabFavorites = document.getElementById("tab-favorites");
      const sectionInfo = document.getElementById("section-info");
      const sectionFavorites = document.getElementById("section-favorites");
      const avatarBlock = document.getElementById("avatar-block");
    
      tabInfo?.addEventListener("click", () => {
        tabInfo.classList.add("active");
        tabFavorites.classList.remove("active");
        sectionInfo.style.display = "block";
        sectionFavorites.style.display = "none";
        if (avatarBlock) avatarBlock.style.display = "flex";
      });
    
      tabFavorites?.addEventListener("click", () => {
        tabFavorites.classList.add("active");
        tabInfo.classList.remove("active");
        sectionFavorites.style.display = "block";
        sectionInfo.style.display = "none";
        if (avatarBlock) avatarBlock.style.display = "none";
      });
    
      // 小鈴鐺 popup(user共用功能)
      const notificationBtn = document.getElementById("notificationBtn");
      const notificationPopup = document.getElementById("notificationPopup");
    
      notificationBtn?.addEventListener("click", () => {
        const isVisible = notificationPopup.style.display === "block";
        notificationPopup.style.display = isVisible ? "none" : "block";
      });
    
      document.addEventListener("click", (e) => {
        if (
          !notificationBtn?.contains(e.target) &&
          !notificationPopup?.contains(e.target)
        ) {
          notificationPopup.style.display = "none";
        }
      });
    
      // 商品篩選 popup(user共用功能)
      const popup = document.getElementById("filter-popup");
      const filterBtn = document.querySelector(".filter-btn");
      const cancelBtn = document.querySelector(".cancel-filter");
      const applyBtn = document.querySelector(".apply-filter");
      const tagButtons = document.querySelectorAll(".tag");
    
      filterBtn?.addEventListener("click", () => {
        popup.classList.remove("hidden");
      });
    
      cancelBtn?.addEventListener("click", () => {
        popup.classList.add("hidden");
      });
    
      tagButtons.forEach(btn => {
        btn.addEventListener("click", () => {
          btn.classList.toggle("active");
        });
      });
    
      applyBtn?.addEventListener("click", () => {
        const selected = [...tagButtons]
          .filter(btn => btn.classList.contains("active"))
          .map(btn => encodeURIComponent(btn.textContent.trim()));
    
        const query = selected.length
          ? `?types=${selected.join(",")}`
          : "";
    
          window.location.href = "user_map.html";
      });
    });
    
    // 商品篩選成功跳轉到 user_map 專屬頁面(user共用功能)
    document.addEventListener("DOMContentLoaded", () => {
      const selectedTags = JSON.parse(localStorage.getItem("selectedTags") || "[]");
    
      const defaultMap = document.getElementById("map-default");
      const filteredMap = document.getElementById("map-filtered");
      const selectedText = document.getElementById("selected-types-text");
      const booths = document.querySelectorAll(".booth");
    
      if (selectedTags.length > 0) {
        defaultMap.style.display = "none";
        filteredMap.style.display = "block";
        selectedText.textContent = `目前篩選：${selectedTags.join("、")}`;
    
        booths.forEach(booth => {
          const tag = booth.dataset.value?.trim();
          if (selectedTags.includes(tag)) {
            booth.classList.add("highlight");
          } else {
            booth.classList.remove("highlight");
          }
        });
      } else {
        defaultMap.style.display = "block";
        filteredMap.style.display = "none";
      }
    });
    ////----- user_markets.html 頁面功能 ----- ////
    // 點擊查看更多看更多市集資訊
    document.addEventListener('DOMContentLoaded', () => {
      const popup = document.getElementById('market-detail-popup');
      const closeBtn = popup.querySelector('.close-popup');
    
      // 綁定每一個 "查看更多" 按鈕的點擊事件
      document.querySelectorAll('.market-card .btn').forEach(button => {
        button.addEventListener('click', async (e) => {
          e.preventDefault();
    
          const card = button.closest('.market-card');
          const title = card.querySelector('h3')?.innerText.trim().split('(')[0];
    
          try {
            const res = await fetch(`/api/market-detail?title=${encodeURIComponent(title)}`);
            const data = await res.json();
    
            // 填入標題與基本資訊
            document.getElementById('popup-title').textContent = data.title;
            document.getElementById('popup-info').innerHTML = `
              <li><strong>活動日期：</strong>${data.date}</li>
              <li><strong>攤攤時間：</strong>${data.time}</li>
              <li><strong>市集地點：</strong>${data.location}</li>
            `;
            document.getElementById('popup-description').textContent = data.description;
    
            // 多張節目表圖片
            const scheduleGallery = document.getElementById('popup-schedule');
            scheduleGallery.innerHTML = '';
            data.scheduleImages.forEach(src => {
              const img = document.createElement('img');
              img.src = src;
              img.alt = '節目表圖片';
              img.style.width = '100%';
              img.style.maxWidth = '500px';
              img.style.borderRadius = '10px';
              img.style.marginBottom = '1rem';
              scheduleGallery.appendChild(img);
            });
    
            // 聯絡資訊
            document.getElementById('popup-contact').innerHTML = `
              <li><strong>Email：</strong>${data.contact.email}</li>
              <li><strong>Instagram：</strong><a href="${data.contact.instagram}" target="_blank">連結</a></li>
              <li><strong>報名連結：</strong><a href="${data.contact.link}" target="_blank">點我前往</a></li>
            `;
    
            popup.classList.remove('hidden');
          } catch (err) {
            alert('無法載入市集資料');
            console.error(err);
          }
        });
      });
    
      // 點擊關閉按鈕時關閉彈出視窗
      if (closeBtn) {
        closeBtn.addEventListener('click', () => {
          popup.classList.add('hidden');
        });
      }
    
      // 點擊彈出視窗背景時關閉彈出視窗
      popup.addEventListener('click', (e) => {
        if (e.target === popup) {
          popup.classList.add('hidden');
        }
      });
    });    
    
    // 市集篩選功能
    document.addEventListener("DOMContentLoaded", () => {
      const filterButtons = document.querySelectorAll(".market-filter-btn");
      const marketCards = document.querySelectorAll(".market-market-card");
    
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
    
    });
    

    ////----- organizer_markets_info.html 頁面功能 ----- ////
    // 左側欄篩選切換功能

    document.querySelectorAll(".tab-btn").forEach((btn) => {
      btn.addEventListener("click", () => {
        document.querySelectorAll(".tab-btn").forEach((b) => b.classList.remove("active"));
        document.querySelectorAll(".tab-section").forEach((sec) => sec.classList.remove("active"));
    
        btn.classList.add("active");
        document.getElementById(btn.dataset.tab).classList.add("active");
      });
    });

    // 時段選擇器
    document.addEventListener("DOMContentLoaded", function () {
      const eventTimesContainer = document.getElementById("event-times-container");
      const addBtn = document.getElementById("add-event-time-btn");
    
      flatpickr("input[type='time']", {
        enableTime: true,
        noCalendar: true,
        dateFormat: "H:i",
        time_24hr: true,
        disableMobile: true
      });
    
      addBtn.addEventListener("click", function () {
        const row = document.createElement("div");
        row.className = "event-time-row";
        row.innerHTML = `
          <input type="date" />
          <input type="time" placeholder="開始時間" />
          <input type="time" placeholder="結束時間" />
          <button class="delete-btn" type="button">❌</button>
        `;
        eventTimesContainer.appendChild(row);
    
        flatpickr(row.querySelectorAll("input[type='time']"), {
          enableTime: true,
          noCalendar: true,
          dateFormat: "H:i",
          time_24hr: true,
          disableMobile: true
        });
    
        row.querySelector(".delete-btn").addEventListener("click", function () {
          row.remove();
        });
      });
    });

    // 市集新增成功
    document.addEventListener("DOMContentLoaded", function () {
      const submitBtn = document.querySelector(".submit");
      const popup = document.getElementById("success-popup");
    
      submitBtn.addEventListener("click", function () {
        popup.classList.remove("hidden");
        popup.classList.add("show");
    
        setTimeout(() => {
          popup.classList.remove("show");
          popup.classList.add("hidden");
        }, 3000);
      });
    });

    ////----- vendor_products.html ----- ////
    // 新增、刪除商品功能
    document.addEventListener("DOMContentLoaded", () => {
      const form = document.getElementById("productForm");
      const productList = document.getElementById("productList");
  
      form.addEventListener("submit", function (e) {
        e.preventDefault(); // 阻止表單送出刷新頁面
  
        const name = form.name.value.trim();
        const type = form.type.value;
        const price = form.price.value.trim();
  
        if (!name || !type || !price) {
          alert("請填寫完整商品資料");
          return;
        }
  
        // 建立卡片
        const card = document.createElement("div");
        card.className = "product-card";
        card.innerHTML = `
          <div class="product-info">
            <span>${name}</span>
            <span>${type}</span>
            <span>$${price}</span>
          </div>
          <div class="product-actions">
            <button class="delete-btn">✕</button>
          </div>
        `;
  
        // 加到清單中
        productList.appendChild(card);
  
        // 清空表單
        form.reset();
  
        // 加入刪除功能
        card.querySelector(".delete-btn").addEventListener("click", () => {
          card.remove();
        });
  
        // 可加入 POST 傳資料 API
        const payload = { name, type, price: Number(price) };
        console.log("📦 POST 資料：", payload);
      });
    });
    
    
    
    
   
      